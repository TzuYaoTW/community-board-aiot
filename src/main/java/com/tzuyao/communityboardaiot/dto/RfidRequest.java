package com.tzuyao.communityboardaiot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class RfidRequest {

    @Schema(description = "住戶地址")
    @NotNull
    private String userAddress;

    @Schema(description = "RFID使用者")
    @NotNull
    private String rfidName;

    @Schema(description = "RFID編號")
    @NotNull
    private String rfidCode;
}
