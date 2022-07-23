package com.tzuyao.communityboardaiot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserRequest {

    @Schema(description = "住戶 ID")
    String userFaceId;

    @Schema(description = "住戶姓名")
    @NotNull
    String userName;

    @Schema(description = "住戶聯絡電話")
    @NotNull
    String userTel;

    @Schema(description = "住戶住址")
    @NotNull
    String userAddress;
}
