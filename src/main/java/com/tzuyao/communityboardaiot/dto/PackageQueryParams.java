package com.tzuyao.communityboardaiot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class PackageQueryParams {

    @Schema(description = "關鍵字搜尋(地址)")
    String search;
    @Schema(description = "包裹狀態(預設0，代表未領取)")
    String state;
    @Schema(description = "根據所選欄位排序(預設last_modified_date)")
    String orderBy;
    @Schema(description = "排序方式(預設DESC，時間由新到舊)")
    String sort;
    @Schema(description = "限制資料筆數(預設5，每次只傳5筆資料)")
    Integer limit;
    @Schema(description = "跳過資料筆數(預設0，不跳過資料，代表第一頁)")
    Integer offset;
}
