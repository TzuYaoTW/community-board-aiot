package com.tzuyao.communityboardaiot.rowmapper;

import com.tzuyao.communityboardaiot.constant.FacilityCategory;
import com.tzuyao.communityboardaiot.model.Facility;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class FacilityRowMapper implements RowMapper<Facility> {
    @Override
    public Facility mapRow(ResultSet resultSet, int i) throws SQLException {

        Facility facility = new Facility();
        facility.setFacilityId(resultSet.getInt("facility_id"));
        facility.setUserAddress(resultSet.getString("user_address"));
        facility.setUserName(resultSet.getString("user_name"));

        String categoryStr = resultSet.getString("facility_category");
        FacilityCategory facilityCategory = FacilityCategory.valueOf(categoryStr);
        facility.setFacilityCategory(facilityCategory);

        facility.setReservedDay(resultSet.getDate("reserved_day").toLocalDate());
        facility.setReservedTime(resultSet.getString("reserved_time"));
        facility.setCreatedDate(resultSet.getTimestamp("created_date"));
        facility.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));
        return facility;
    }
}
