package com.tzuyao.communityboardaiot.rowmapper;

import com.tzuyao.communityboardaiot.model.Admin;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminRowMapper implements RowMapper<Admin> {
    @Override
    public Admin mapRow(ResultSet resultSet, int i) throws SQLException {

        Admin admin = new Admin();

        admin.setAdminId(resultSet.getInt("admin_id"));
        admin.setAdminAccount(resultSet.getString("admin_account"));
        admin.setAdminPassword(resultSet.getString("admin_password"));
        admin.setAdminName(resultSet.getString("admin_name"));
        admin.setAdminTel(resultSet.getString("admin_tel"));
        admin.setAdminEmail(resultSet.getString("admin_email"));
        admin.setAdminAddress(resultSet.getString("admin_address"));
        admin.setCreatedDate(resultSet.getTimestamp("created_date"));
        admin.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));

        return admin;
    }
}
