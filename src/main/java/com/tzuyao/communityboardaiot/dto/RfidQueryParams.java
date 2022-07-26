package com.tzuyao.communityboardaiot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class RfidQueryParams {

    @Schema(description = "關鍵字搜尋(地址)")
    private String searchByAddress;

    @Schema(description = "關鍵字搜尋(RFID CODE)")
    private String searchByRfidCode;

}
