package com.tzuyao.communityboardaiot.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class Package {
    @Schema(description = "包裹 ID")
    Integer packageId;

    @Schema(description = "收件人地址")
    String userAddress;

    @Schema(description = "收件人姓名")
    String packageName;

    @Schema(description = "包裹編號")
    String packageNumber;

    @Schema(description = "包裹狀態(0:未領取，1:已領取)")
    String packageState;

    @Schema(description = "包裹數據建立時間")
    Date createdDate;

    @Schema(description = "包裹數據最後修改時間")
    Date lastModifiedDate;
}
