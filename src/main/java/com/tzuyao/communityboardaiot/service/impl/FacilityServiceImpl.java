package com.tzuyao.communityboardaiot.service.impl;

import com.tzuyao.communityboardaiot.dao.FacilityDao;
import com.tzuyao.communityboardaiot.dto.FacilityQueryParams;
import com.tzuyao.communityboardaiot.dto.FacilityRequest;
import com.tzuyao.communityboardaiot.model.Facility;
import com.tzuyao.communityboardaiot.service.FacilityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class FacilityServiceImpl implements FacilityService {

    @Autowired
    private FacilityDao facilityDao;

    @Override
    public Facility getFacilityById(Integer facilityId) {
        return facilityDao.getFacilityById(facilityId);
    }

    @Override
    public Integer createFacility(FacilityRequest facilityRequest) {
        return facilityDao.createFacility(facilityRequest);
    }

    @Override
    public void updateFacility(Integer facilityId, FacilityRequest facilityRequest) {
        facilityDao.updateFacility(facilityId, facilityRequest);
    }

    @Override
    public void deleteFacility(Integer facilityId) {
        facilityDao.deleteFacility(facilityId);
    }

    @Override
    public List<Facility> getFacilities(FacilityQueryParams facilityQueryParams) {
        return facilityDao.getFacilities(facilityQueryParams);
    }
}
