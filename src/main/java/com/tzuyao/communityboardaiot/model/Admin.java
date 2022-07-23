package com.tzuyao.communityboardaiot.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class Admin {

    @Schema(description = "管理員 ID")
    private Integer adminId;

    @Schema(description = "管理員帳號")
    private String adminAccount;

    @Schema(description = "管理員密碼")
    private String adminPassword;

    @Schema(description = "管理員姓名")
    private String adminName;

    @Schema(description = "管理員電話")
    private String adminTel;

    @Schema(description = "管理員 email")
    private String adminEmail;

    @Schema(description = "管理員地址")
    private String adminAddress;

    @Schema(description = "管理員資料被創建時間")
    private Date createdDate;

    @Schema(description = "管理員資料最後修改時間")
    private Date lastModifiedDate;
}
