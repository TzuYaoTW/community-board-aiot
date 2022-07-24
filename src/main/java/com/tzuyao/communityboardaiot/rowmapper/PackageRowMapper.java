package com.tzuyao.communityboardaiot.rowmapper;

import com.tzuyao.communityboardaiot.model.Package;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PackageRowMapper implements RowMapper<Package> {
    @Override
    public Package mapRow(ResultSet resultSet, int i) throws SQLException {

        Package aPackage = new Package();
        aPackage.setPackageId(resultSet.getInt("package_id"));
        aPackage.setUserAddress(resultSet.getString("user_address"));
        aPackage.setPackageName(resultSet.getString("package_name"));
        aPackage.setPackageNumber(resultSet.getString("package_number"));
        aPackage.setPackageState(resultSet.getString("package_state"));
        aPackage.setCreatedDate(resultSet.getTimestamp("created_date"));
        aPackage.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));

        return aPackage;
    }
}
