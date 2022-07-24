package com.tzuyao.communityboardaiot.util;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class Page<T> {

    @Schema(description = "限制資料庫回傳筆數(每個分頁要顯示幾筆數據)")
    private Integer limit;

    @Schema(description = "跳過幾筆數據(例如limit預設=5，第一頁offset=0，第二頁offset=5....)")
    private Integer offset;

    @Schema(description = "查詢結果筆數，計算分頁數量用")
    private Integer total;

    @Schema(description = "住戶資料集")
    private List<T> results;
}
