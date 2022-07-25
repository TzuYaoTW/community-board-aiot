package com.tzuyao.communityboardaiot.service.impl;

import com.tzuyao.communityboardaiot.dao.FacialRecognitionDao;
import com.tzuyao.communityboardaiot.model.User;
import com.tzuyao.communityboardaiot.service.FacialRecognitionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class FacialRecognitionServiceImpl implements FacialRecognitionService {

    @Autowired
    private FacialRecognitionDao facialRecognitionDao;

    @Override
    public User getUserByFaceId(String faceId) {
        return facialRecognitionDao.getUserByFaceId(faceId);
    }
}
