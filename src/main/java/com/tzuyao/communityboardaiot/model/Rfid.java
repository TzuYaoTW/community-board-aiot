package com.tzuyao.communityboardaiot.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class Rfid {

    @Schema(description = "RFID流水號")
    private Integer rfidId;
    @Schema(description = "住戶地址")
    private String userAddress;
    @Schema(description = "RFID擁有者")
    private String rfidName;
    @Schema(description = "RFID ID")
    private String rfidCode;
    @Schema(description = "RFID 資料創建時間")
    private Date createdDate;
    @Schema(description = "RFID 資料最後修改時間")
    private Date lastModifiedDate;

}
