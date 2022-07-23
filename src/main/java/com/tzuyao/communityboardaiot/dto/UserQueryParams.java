package com.tzuyao.communityboardaiot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data

public class UserQueryParams {
    @Schema(description = "關鍵字搜尋(姓名)")
    String search;
    @Schema(description = "根據所選欄位排序")
    String orderBy;
    @Schema(description = "排序方式")
    String sort;
    @Schema(description = "限制資料筆數")
    Integer limit;
    @Schema(description = "跳過資料筆數")
    Integer offset;
}
