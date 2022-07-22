package com.tzuyao.communityboardaiot.dto;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class AdminRequest {

    @NotNull
    private String adminAccount;
    @NotNull
    private String adminPassword;
    @NotNull
    private String adminName;
    @NotNull
    private String adminTel;
    @NotNull
    @Email
    private String adminEmail;
    @NotNull
    private String adminAddress;
}
