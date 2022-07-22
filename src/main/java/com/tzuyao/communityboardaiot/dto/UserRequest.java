package com.tzuyao.communityboardaiot.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UserRequest {

    String userFaceId;
    @NotNull
    String userName;
    @NotNull
    String userTel;
    @NotNull
    String userAddress;
}
