package com.tzuyao.communityboardaiot.dao;

import com.tzuyao.communityboardaiot.model.User;

public interface FacialRecognitionDao {

    User getUserByFaceId(String faceId);
}
