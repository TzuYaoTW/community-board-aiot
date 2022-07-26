package com.tzuyao.communityboardaiot.rowmapper;

import com.tzuyao.communityboardaiot.model.Rfid;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RfidRowMapper implements RowMapper<Rfid> {
    @Override
    public Rfid mapRow(ResultSet resultSet, int i) throws SQLException {

        Rfid rfid = new Rfid();
        rfid.setRfidId(resultSet.getInt("rfid_id"));
        rfid.setUserAddress(resultSet.getString("user_address"));
        rfid.setRfidName(resultSet.getString("rfid_name"));
        rfid.setRfidCode(resultSet.getString("rfid_code"));
        rfid.setCreatedDate(resultSet.getTimestamp("created_date"));
        rfid.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));

        return rfid;
    }
}
