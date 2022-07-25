package com.tzuyao.communityboardaiot.service;

import com.tzuyao.communityboardaiot.model.User;

public interface FacialRecognitionService {
    User getUserByFaceId(String faceId);
}
