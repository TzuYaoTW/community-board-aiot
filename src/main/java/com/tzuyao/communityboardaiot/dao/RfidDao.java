package com.tzuyao.communityboardaiot.dao;

import com.tzuyao.communityboardaiot.dto.RfidQueryParams;
import com.tzuyao.communityboardaiot.dto.RfidRequest;
import com.tzuyao.communityboardaiot.model.Rfid;

import java.util.List;

public interface RfidDao {
    Rfid getRfidById(Integer rfidId);

    Integer createRfid(RfidRequest rfidRequest);

    void deleteRfid(Integer rfidId);

    void updateRfid(RfidRequest rfidRequest, Integer rfidId);

    List<Rfid> getRfidList(RfidQueryParams rfidQueryParams);
}
