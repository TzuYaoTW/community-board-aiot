package com.tzuyao.communityboardaiot.service;

import com.tzuyao.communityboardaiot.model.Admin;
import com.tzuyao.communityboardaiot.dto.AdminRequest;

import java.util.List;

public interface AdminService {

    List<Admin> getAdmins();

    Integer createAdmin(AdminRequest adminRequest);

    Admin getAdminById(Integer adminId);

    void deleteAdminById(Integer adminId);

    void updateAdmin(Integer adminId, AdminRequest adminRequest);

}
