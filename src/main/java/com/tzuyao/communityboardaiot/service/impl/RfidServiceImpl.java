package com.tzuyao.communityboardaiot.service.impl;

import com.tzuyao.communityboardaiot.dao.RfidDao;
import com.tzuyao.communityboardaiot.dto.RfidQueryParams;
import com.tzuyao.communityboardaiot.dto.RfidRequest;
import com.tzuyao.communityboardaiot.model.Rfid;
import com.tzuyao.communityboardaiot.service.RfidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RfidServiceImpl implements RfidService {

    @Autowired
    private RfidDao rfidDao;

    @Override
    public Rfid getRfidById(Integer rfidId) {
        return rfidDao.getRfidById(rfidId);
    }

    @Override
    public Integer createRfid(RfidRequest rfidRequest) {
        return rfidDao.createRfid(rfidRequest);
    }

    @Override
    public void deleteRfid(Integer rfidId) {
        rfidDao.deleteRfid(rfidId);
    }

    @Override
    public void updateRfid(RfidRequest rfidRequest, Integer rfidId) {
        rfidDao.updateRfid(rfidRequest, rfidId);
    }

    @Override
    public List<Rfid> getRfidList(RfidQueryParams rfidQueryParams) {
        return rfidDao.getRfidList(rfidQueryParams);
    }
}
