package com.tzuyao.communityboardaiot.dao.impl;

import com.tzuyao.communityboardaiot.dao.FacilityDao;
import com.tzuyao.communityboardaiot.dto.FacilityQueryParams;
import com.tzuyao.communityboardaiot.dto.FacilityRequest;
import com.tzuyao.communityboardaiot.model.Facility;
import com.tzuyao.communityboardaiot.rowmapper.FacilityRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FacilityDaoImpl implements FacilityDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Facility getFacilityById(Integer facilityId) {

        String sql = "SELECT facility_id, user_address, user_name, facility_category, reserved_day, reserved_time, " +
                "created_date, last_modified_date FROM facility WHERE facility_id = :facilityId";

        Map<String, Object> map = new HashMap<>();
        map.put("facilityId", facilityId);

        List<Facility> facilityList = namedParameterJdbcTemplate.query(sql, map, new FacilityRowMapper());

        if (facilityList.size() > 0) {
            return facilityList.get(0);
        }else {
            return null;
        }


    }

    @Override
    public Integer createFacility(FacilityRequest facilityRequest) {
        String sql = "INSERT INTO facility(user_address, user_name, facility_category, " +
                "reserved_day, reserved_time, created_date, last_modified_date) " +
                "VALUES (:userAddress, :userName, :facilityCategory, " +
                ":reservedDay, :reservedTime, :createDate, :lastModifiedDate);";

        Map<String, Object> map = new HashMap<>();
        map.put("userAddress", facilityRequest.getUserAddress());
        map.put("userName", facilityRequest.getUserName());
        map.put("facilityCategory", facilityRequest.getFacilityCategory().toString());
        map.put("reservedDay", facilityRequest.getReservedDay());
        map.put("reservedTime", facilityRequest.getReservedTime());
        map.put("createDate", new Date());
        map.put("lastModifiedDate", new Date());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        Integer facilityId = keyHolder.getKey().intValue();

        return facilityId;
    }

    @Override
    public void updateFacility(Integer facilityId, FacilityRequest facilityRequest) {
        String sql = "UPDATE facility SET user_address = :userAddress, user_name = :userName, " +
                "facility_category = :facilityCategory, reserved_day = :reservedDay, " +
                "reserved_time = :reservedTime, last_modified_date = :lastModifiedDate " +
                "WHERE facility_id = :facilityId;";
        Map<String, Object> map = new HashMap<>();
        map.put("userAddress", facilityRequest.getUserAddress());
        map.put("userName", facilityRequest.getUserName());
        map.put("facilityCategory", facilityRequest.getFacilityCategory().toString());
        map.put("reservedDay", facilityRequest.getReservedDay());
        map.put("reservedTime",facilityRequest.getReservedTime());
        map.put("lastModifiedDate", new Date());
        map.put("facilityId", facilityId);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteFacility(Integer facilityId) {
        String sql = "DELETE FROM facility WHERE facility_id = :facilityId;";
        Map<String, Object> map = new HashMap<>();
        map.put("facilityId", facilityId);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public List<Facility> getFacilities(FacilityQueryParams facilityQueryParams) {
        String sql = "SELECT facility_id, user_address, user_name, facility_category, " +
                "reserved_day, reserved_time, created_date, last_modified_date " +
                "FROM facility WHERE 1=1";
        Map<String, Object> map = new HashMap<>();
        if (facilityQueryParams.getUserName() != null) {
            sql = sql + " AND user_name LIKE :userName";
            map.put("userName", "%" + facilityQueryParams.getUserName() + "%");
        }
        if (facilityQueryParams.getFacilityCategory() != null) {
            sql = sql + " AND facility_category = :category";
            map.put("category", facilityQueryParams.getFacilityCategory().toString());
        }
        if (facilityQueryParams.getReservedDay() != null) {
            sql = sql + " AND reserved_day = :day";
            map.put("day", facilityQueryParams.getReservedDay());
        }

        if (facilityQueryParams.getToday() != null ){
            sql = sql + " AND reserved_day > :today";
            map.put("today", LocalDate.now());
        }

        if (facilityQueryParams.getReservedTime() != null ){
            sql = sql + " AND reserved_time = :time";
            map.put("time", facilityQueryParams.getReservedTime());
        }

        sql = sql + " ORDER BY " + facilityQueryParams.getOrderBy() + " " + facilityQueryParams.getSort();

        List<Facility> facilityList = namedParameterJdbcTemplate.query(sql, map, new FacilityRowMapper());

        return facilityList;
    }
}
