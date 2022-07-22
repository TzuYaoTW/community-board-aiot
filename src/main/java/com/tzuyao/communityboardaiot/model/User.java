package com.tzuyao.communityboardaiot.model;

import lombok.Data;

import java.util.Date;

@Data
public class User {
   private Integer userId;
   private String userFaceId;
   private String userName;
   private String userTel;
   private String userAddress;
   private Date createdDate;
   private Date lastModifiedDate;
}
