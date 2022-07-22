package com.tzuyao.communityboardaiot.model;

import lombok.Data;

import java.util.Date;

@Data
public class Admin {

    private Integer adminId;
    private String adminAccount;
    private String adminPassword;
    private String adminName;
    private String adminTel;
    private String adminEmail;
    private String adminAddress;
    private Date createdDate;
    private Date lastModifiedDate;
}
