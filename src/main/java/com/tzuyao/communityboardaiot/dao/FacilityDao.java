package com.tzuyao.communityboardaiot.dao;

import com.tzuyao.communityboardaiot.dto.FacilityQueryParams;
import com.tzuyao.communityboardaiot.dto.FacilityRequest;
import com.tzuyao.communityboardaiot.model.Facility;

import java.util.List;

public interface FacilityDao {

    Facility getFacilityById(Integer facilityId);

    Integer createFacility(FacilityRequest facilityRequest);

    void updateFacility(Integer facilityId, FacilityRequest facilityRequest);

    void deleteFacility(Integer facilityId);

    List<Facility> getFacilities(FacilityQueryParams facilityQueryParams);
}
