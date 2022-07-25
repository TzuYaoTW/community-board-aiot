package com.tzuyao.communityboardaiot.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class PackageRequest {

    @NotNull
    @Schema(description = "收件人地址")
    String userAddress;

    @NotNull
    @Schema(description = "收件人姓名")
    String packageName;

    @NotNull
    @Schema(description = "包裹編號")
    String packageNumber;
}
