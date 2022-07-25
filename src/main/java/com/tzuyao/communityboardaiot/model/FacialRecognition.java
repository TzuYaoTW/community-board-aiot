package com.tzuyao.communityboardaiot.model;


import io.swagger.v3.oas.annotations.media.Schema;

public class FacialRecognition {

    @Schema(description = "人臉辨識結果(由RaspberryPi傳入)")
    private static String facialRecognitionId;
    @Schema(description = "token令牌(當RaspberryPi傳入人臉辨識結果後產生)")
    private static String token;

    public static String getFacialRecognitionId() {
        return facialRecognitionId;
    }

    public static void setFacialRecognitionId(String facialRecognitionId) {
        FacialRecognition.facialRecognitionId = facialRecognitionId;
    }

    public static String getToken() {
        return token;
    }

    public static void setToken(String token) {
        FacialRecognition.token = token;
    }
}
