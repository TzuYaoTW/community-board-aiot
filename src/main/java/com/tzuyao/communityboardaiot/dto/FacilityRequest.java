package com.tzuyao.communityboardaiot.dto;

import com.tzuyao.communityboardaiot.constant.FacilityCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class FacilityRequest {

    @Schema(description = "預定者地址")
    @NotNull
    private String userAddress;

    @Schema(description = "預定者姓名")
    @NotNull
    private String userName;

    @Schema(description = "場地")
    @NotNull
    private FacilityCategory facilityCategory;

    @Schema(description = "預定日期")
    @NotNull
    private LocalDate reservedDay;

    @Schema(description = "預定時間")
    @NotNull
    private String reservedTime;

}
