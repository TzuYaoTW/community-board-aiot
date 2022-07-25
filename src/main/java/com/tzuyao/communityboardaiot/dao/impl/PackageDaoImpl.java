package com.tzuyao.communityboardaiot.dao.impl;

import com.tzuyao.communityboardaiot.dao.PackageDao;
import com.tzuyao.communityboardaiot.dto.PackageQueryParams;
import com.tzuyao.communityboardaiot.dto.PackageRequest;
import com.tzuyao.communityboardaiot.model.Package;
import com.tzuyao.communityboardaiot.rowmapper.PackageRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class PackageDaoImpl implements PackageDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Package> getPackages(PackageQueryParams packageQueryParams) {
        String sql = "SELECT package_id, user_address, package_name, package_number, " +
                "package_state, created_date, last_modified_date FROM package WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        sql = addFilteringSql(sql, map, packageQueryParams);

        sql = sql + " ORDER BY " + packageQueryParams.getOrderBy() + " " + packageQueryParams.getSort();

        sql = sql + " LIMIT :limit OFFSET :offset";
        map.put("limit", packageQueryParams.getLimit());
        map.put("offset", packageQueryParams.getOffset());

        List<Package> packageList = namedParameterJdbcTemplate.query(sql, map, new PackageRowMapper());

        return packageList;
    }

    @Override
    public Integer countPackage(PackageQueryParams packageQueryParams) {

        String sql = "SELECT COUNT(*) FROM package WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        sql = addFilteringSql(sql, map, packageQueryParams);

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    @Override
    public Package getPackageById(Integer packageId) {
        String sql = "SELECT package_id, user_address, package_name, package_number, " +
                "package_state, created_date, last_modified_date FROM `package` " +
                "WHERE package_id = :packageId;";

        Map<String, Object> map = new HashMap<>();
        map.put("packageId", packageId);

        List<Package> packageList = namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource(map), new PackageRowMapper());

        if (packageList.size() > 0) {
            return packageList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public Integer createPackage(PackageRequest packageRequest, String state) {
        String sql = "INSERT INTO `package`(user_address, package_name, package_number, package_state, " +
                "created_date, last_modified_date) VALUES(:userAddress, :packageName, :packageNumber, :packageState, " +
                ":createdDate, :lastModifiedDate);";

        Map<String, Object> map = new HashMap<>();
        map.put("userAddress", packageRequest.getUserAddress());
        map.put("packageName", packageRequest.getPackageName());
        map.put("packageNumber", packageRequest.getPackageNumber());
        map.put("packageState", state);
        map.put("createdDate", new Date());
        map.put("lastModifiedDate", new Date());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        Integer packageId = keyHolder.getKey().intValue();

        return packageId;
    }

    @Override
    public void deletePackage(Integer packageId) {
        String sql = "DELETE FROM package WHERE package_id = :packageId;";
        Map<String, Object> map = new HashMap<>();
        map.put("packageId", packageId);
        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void updatePackage(PackageRequest packageRequest, Integer packageId, String state) {
        String sql = "UPDATE `package` SET user_address = :userAddress, package_name = :packageName, " +
                "package_number = :packageNumber, package_state = :packageState, " +
                "last_modified_date = :lastModifiedDate WHERE package_id = :packageId;";
        Map<String, Object> map = new HashMap<>();
        map.put("userAddress", packageRequest.getUserAddress());
        map.put("packageName", packageRequest.getPackageName());
        map.put("packageNumber", packageRequest.getPackageNumber());
        map.put("packageState", state);
        map.put("lastModifiedDate", new Date());
        map.put("packageId", packageId);

        namedParameterJdbcTemplate.update(sql, map);
    }

    private String addFilteringSql(String sql, Map<String, Object> map, PackageQueryParams packageQueryParams) {
        if (packageQueryParams.getSearch() != null){
            sql = sql + " AND user_address LIKE :search";
            map.put("search", "%" + packageQueryParams.getSearch() + "%");
            System.out.println(packageQueryParams.getSearch());
        }

        sql = sql + " AND package_state = :state";
        map.put("state", packageQueryParams.getState());

        return sql;
    }
}
