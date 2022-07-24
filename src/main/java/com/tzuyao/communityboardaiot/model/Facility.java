package com.tzuyao.communityboardaiot.model;

import com.tzuyao.communityboardaiot.constant.FacilityCategory;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class Facility {
    private Integer facilityId;
    private String userAddress;
    private String userName;
    private FacilityCategory facilityCategory;
    private LocalDate reservedDay;
    private String reservedTime;
    private Date createdDate;
    private Date lastModifiedDate;
}
