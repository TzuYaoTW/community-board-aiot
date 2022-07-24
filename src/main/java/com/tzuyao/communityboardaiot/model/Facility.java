package com.tzuyao.communityboardaiot.model;

import com.tzuyao.communityboardaiot.constant.FacilityCategory;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Facility {
    @Schema(description = "公共設施 ID")
    private Integer facilityId;

    @Schema(description = "租借人地址")
    private String userAddress;

    @Schema(description = "租借人姓名")
    private String userName;

    @Schema(description = "租借人租的設施")
    private FacilityCategory facilityCategory;

    @Schema(description = "預定的日期")
    private LocalDate reservedDay;

    @Schema(description = "預定的時間(ex:10:00-12:00)")
    private String reservedTime;

    @Schema(description = "創建預定數據時間")
    private Date createdDate;

    @Schema(description = "最後修改預定數據時間")
    private Date lastModifiedDate;
}
