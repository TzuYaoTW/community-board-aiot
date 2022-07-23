package com.tzuyao.communityboardaiot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class AdminRequest {

    @Schema(description = "管理員帳號")
    @NotNull
    private String adminAccount;

    @Schema(description = "管理員密碼")
    @NotNull
    private String adminPassword;

    @Schema(description = "管理員姓名")
    @NotNull
    private String adminName;

    @Schema(description = "管理員電話")
    @NotNull
    private String adminTel;

    @Schema(description = "管理員信箱")
    @NotNull
    @Email
    private String adminEmail;

    @Schema(description = "管理員地址")
    @NotNull
    private String adminAddress;
}
