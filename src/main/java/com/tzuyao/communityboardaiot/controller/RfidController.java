package com.tzuyao.communityboardaiot.controller;

import com.tzuyao.communityboardaiot.dto.RfidQueryParams;
import com.tzuyao.communityboardaiot.dto.RfidRequest;
import com.tzuyao.communityboardaiot.model.Rfid;
import com.tzuyao.communityboardaiot.service.RfidService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Tag(name = "RFID CRUD", description = "管理員可以新增查詢修改刪除關於RFID的資料")
@RestController
public class RfidController {

    @Autowired
    private RfidService rfidService;


    @Operation(summary = "取的RFID資料列表(模糊查詢地址、RFID CODE)", description = "可模糊查詢地址與RFID CODE，取得RFID資料列表")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Rfid.class))
                            )
                    })
    })
    @GetMapping("/rfids")
    public ResponseEntity<List<Rfid>> getRfidList(@RequestParam(required = false)
                                                      @Parameter(description = "RFID擁有者地址") String address,
                                         @RequestParam(required = false)
                                         @Parameter(description = "RFID") String rfid) {
        RfidQueryParams rfidQueryParams = new RfidQueryParams();
        rfidQueryParams.setSearchByAddress(address);
        rfidQueryParams.setSearchByRfidCode(rfid);

        List<Rfid> rfidList = rfidService.getRfidList(rfidQueryParams);

        return ResponseEntity.status(HttpStatus.OK).body(rfidList);
    }

    @Operation(summary = "取得RFID資料", description = "透過rfid_id取的資料並回傳前端")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Rfid.class))
                            )
                    })
    })
    @GetMapping("/rfids/{rfidId}")
    public ResponseEntity<Rfid> getRfidById(@PathVariable Integer rfidId){
        Rfid rfid = rfidService.getRfidById(rfidId);
        return ResponseEntity.status(HttpStatus.OK).body(rfid);
    }

    @Operation(summary = "新增RFID資料", description = "新增RFID資料並回傳新增的RFID資料數據")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Rfid.class))
                            )
                    })
    })
    @PostMapping("/rfids")
    public ResponseEntity<Rfid> createRfid(@RequestBody @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody
            (description = "請求參數列表") RfidRequest rfidRequest){
        Integer rfidId = rfidService.createRfid(rfidRequest);
        Rfid rfid = rfidService.getRfidById(rfidId);

        return ResponseEntity.status(HttpStatus.OK).body(rfid);
    }

    @Operation(summary = "修改RFID資料", description = "透過rfid_id修改RFID資料數據")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Rfid.class))
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "無此 RFID ID，無法執行修改功能", content = {
                    @Content()
            })
    })
    @PutMapping("/rfids/{rfidId}")
    public ResponseEntity updateRfid(@PathVariable Integer rfidId,
                                     @RequestBody @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody
                                             (description = "請求參數列表") RfidRequest rfidRequest) {
        Rfid rfid = rfidService.getRfidById(rfidId);
        if (rfid == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        rfidService.updateRfid(rfidRequest, rfidId);
        Rfid updateRfid = rfidService.getRfidById(rfidId);
        return ResponseEntity.status(HttpStatus.OK).body(updateRfid);

    }

    @Operation(summary = "刪除RFID資料", description = "透過rfid_id刪除RFID資料數據")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功",
                    content = {
                            @Content()
                    })
    })
    @DeleteMapping("/rfids/{rfidId}")
    public ResponseEntity deleteRfid(@PathVariable Integer rfidId) {
        rfidService.deleteRfid(rfidId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
