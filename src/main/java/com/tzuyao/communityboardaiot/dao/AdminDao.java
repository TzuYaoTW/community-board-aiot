package com.tzuyao.communityboardaiot.dao;

import com.tzuyao.communityboardaiot.model.Admin;
import com.tzuyao.communityboardaiot.dto.AdminRequest;

import java.util.List;

public interface AdminDao {

    List<Admin> getAdmins();

    Integer createAdmin(AdminRequest adminRequest);

    Admin getAdminById(Integer adminId);

    void deleteAdmin(Integer adminId);

    void updateAdmin(Integer adminId, AdminRequest adminRequest);
}
