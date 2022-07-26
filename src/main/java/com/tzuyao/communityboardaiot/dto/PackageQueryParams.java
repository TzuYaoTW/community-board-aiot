package com.tzuyao.communityboardaiot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PackageQueryParams {

    @Schema(description = "關鍵字搜尋(地址)")
    private String searchByAddress;

    @Schema(description = "關鍵字搜尋(RFID)")
    private String searchByRfid;

    @Schema(description = "包裹狀態(預設0，代表未領取)")
    private String state;

    @Schema(description = "根據所選欄位排序(預設last_modified_date)")
    private String orderBy;

    @Schema(description = "排序方式(預設DESC，時間由新到舊)")
    private String sort;

    @Schema(description = "限制資料筆數(預設5，每次只傳5筆資料)")
    private Integer limit;

    @Schema(description = "跳過資料筆數(預設0，不跳過資料，代表第一頁)")
    private Integer offset;
}
