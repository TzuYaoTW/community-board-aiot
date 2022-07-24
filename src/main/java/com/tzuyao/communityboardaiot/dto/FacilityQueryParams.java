package com.tzuyao.communityboardaiot.dto;

import com.tzuyao.communityboardaiot.constant.FacilityCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FacilityQueryParams {

    @Schema(description = "預定人")
    private String userName;

    @Schema(description = "預定設施")
    private FacilityCategory facilityCategory;

    @Schema(description = "預定日期")
    private LocalDate reservedDay;

    @Schema(description = "預定時段")
    private String reservedTime;

    @Schema(description = "使用系統日期，抓取時間還沒到的預定")
    private String today;

    @Schema(description = "根據所選欄位排序")
    private String orderBy;

    @Schema(description = "排序方式")
    private String sort;
}
