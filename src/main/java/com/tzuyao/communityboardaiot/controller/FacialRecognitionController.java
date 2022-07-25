package com.tzuyao.communityboardaiot.controller;

import com.tzuyao.communityboardaiot.model.FacialRecognition;
import com.tzuyao.communityboardaiot.model.User;
import com.tzuyao.communityboardaiot.service.FacialRecognitionService;
import com.tzuyao.communityboardaiot.util.Token;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "人臉辨識", description = "接收RaspberryPi人臉辨識結果，將其結果取得住戶相關資料")
@RestController
public class FacialRecognitionController {

    @Autowired
    private FacialRecognitionService facialRecognitionService;

    @Operation(summary = "接收Raspberry Pi的人臉辨識答案", description = "" +
            "將人臉辨識答案轉變為token，並且存至Java Bean(方便後續比對)，" +
            "並且回傳token給Raspberry Pi")
    @GetMapping("/Demo/pi")
    public ResponseEntity<?> setFacialRecognition(@RequestParam String user) {
        FacialRecognition.setFacialRecognitionId(user);
        System.out.println(user);
        String token = new Token().verify();
        FacialRecognition.setToken(token);
        System.out.println(token);

        return ResponseEntity.status(HttpStatus.ACCEPTED).body(token);
    }

    @Operation(summary = "接收前端人臉辨識token，並且取得對應的住戶資料", description = "" +
            "比較我傳出去的token和前端傳來的token(這個token是Raspberry Pi傳給前端的)是一致的，" +
            "用意是不希望有他人透過非人臉辨識途徑來拿到資料庫數據")
    @GetMapping("/chrome")
    public ResponseEntity<?> getUserByFaceId(@RequestParam String token) {
        if (FacialRecognition.getToken().equals(token)) {
            System.out.println("token比對成功");
            String faceId = FacialRecognition.getFacialRecognitionId();
            User user = facialRecognitionService.getUserByFaceId(faceId);
            if (user != null) {
                return ResponseEntity.status(HttpStatus.OK).body(user);
            }else{
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }

        }else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


}
