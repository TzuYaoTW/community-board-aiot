package com.tzuyao.communityboardaiot.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
public class User {

   @Schema(description = "住戶 ID")
   private Integer userId;

   @Schema(description = "住戶 人臉辨識 ID")
   private String userFaceId;

   @Schema(description = "住戶姓名")
   private String userName;

   @Schema(description = "住戶聯絡電話")
   private String userTel;

   @Schema(description = "住戶地址")
   private String userAddress;

   @Schema(description = "住戶資料被建立時間")
   private Date createdDate;

   @Schema(description = "住戶資料最後修改時間")
   private Date lastModifiedDate;
}
