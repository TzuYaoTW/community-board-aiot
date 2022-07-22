package com.tzuyao.communityboardaiot.dao.impl;

import com.tzuyao.communityboardaiot.model.Admin;
import com.tzuyao.communityboardaiot.dao.AdminDao;
import com.tzuyao.communityboardaiot.dto.AdminRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import com.tzuyao.communityboardaiot.rowmapper.AdminRowMapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class AdminDaoImpl implements AdminDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<Admin> getAdmins() {

        String sql = "SELECT admin_id, admin_account, admin_password, admin_name, " +
                "admin_tel, admin_email, admin_address, created_date, last_modified_date " +
                "FROM admin ";
        Map<String, Object> map = new HashMap<>();
        List<Admin> adminList = namedParameterJdbcTemplate.query(sql, map, new AdminRowMapper());

        return adminList;
    }

    @Override
    public Integer createAdmin(AdminRequest adminRequest) {

        String sql = "INSERT INTO admin(admin_account, admin_password, admin_name, admin_tel," +
                " admin_email, admin_address, created_date, last_modified_date)" +
                " VALUES (:adminAccount, :adminPassword, :adminName, :adminTel," +
                " :adminEmail, :adminAddress, :createdDate, :lastModifiedDate);";

        Map<String, Object> map = new HashMap<>();
        map.put("adminAccount", adminRequest.getAdminAccount());
        map.put("adminPassword", adminRequest.getAdminPassword());
        map.put("adminName", adminRequest.getAdminName());
        map.put("adminTel", adminRequest.getAdminTel());
        map.put("adminEmail", adminRequest.getAdminEmail());
        map.put("adminAddress", adminRequest.getAdminAddress());
        Date now = new Date();
        map.put("createdDate", now);
        map.put("lastModifiedDate", now);

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int adminId = keyHolder.getKey().intValue();

        return adminId;
    }

    @Override
    public Admin getAdminById(Integer adminId) {

        String sql = "SELECT admin_id, admin_account, admin_password, admin_name, admin_tel," +
                " admin_email, admin_address, created_date, last_modified_date " +
                "FROM admin WHERE admin_id = :adminId;";

        Map<String, Object> map = new HashMap<>();
        map.put("adminId", adminId);

        List<Admin> adminList = namedParameterJdbcTemplate.query(sql, map, new AdminRowMapper());

        if (adminList.size() > 0){
            return adminList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public void deleteAdminById(Integer adminId) {

        String sql = "DELETE FROM admin WHERE admin_id = :adminId;";

        Map<String, Object> map = new HashMap<>();
        map.put("adminId", adminId);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void updateAdmin(Integer adminId, AdminRequest adminRequest) {

        String sql = "UPDATE admin SET admin_account = :adminAccount, admin_password = :adminPassword," +
                " admin_name = :adminName, admin_tel = :adminTel, admin_email = :adminEmail," +
                " admin_address = :adminAddress, last_modified_date = :lastModifiedDate" +
                " WHERE admin_id = :adminId;";
        Map<String, Object> map = new HashMap<>();
        map.put("adminAccount", adminRequest.getAdminAccount());
        map.put("adminPassword", adminRequest.getAdminPassword());
        map.put("adminName", adminRequest.getAdminName());
        map.put("adminTel", adminRequest.getAdminTel());
        map.put("adminEmail", adminRequest.getAdminEmail());
        map.put("adminAddress", adminRequest.getAdminAddress());
        map.put("lastModifiedDate", new Date());
        map.put("adminId", adminId);

        namedParameterJdbcTemplate.update(sql, map);
    }
}