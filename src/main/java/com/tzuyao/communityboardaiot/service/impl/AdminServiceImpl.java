package com.tzuyao.communityboardaiot.service.impl;

import com.tzuyao.communityboardaiot.dao.AdminDao;
import com.tzuyao.communityboardaiot.dto.AdminRequest;
import com.tzuyao.communityboardaiot.model.Admin;
import com.tzuyao.communityboardaiot.service.AdminService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Component
public class AdminServiceImpl implements AdminService {

    private final static Logger log = LoggerFactory.getLogger(AdminServiceImpl.class);

    @Autowired
    private AdminDao adminDao;

    @Override
    public List<Admin> getAdmins() {
        return adminDao.getAdmins();
    }

    @Override
    public Integer createAdmin(AdminRequest adminRequest) {
        List<Admin> adminList = adminDao.getAdmins();
        for (Admin admin : adminList) {
            if (admin.getAdminAccount().equals(adminRequest.getAdminAccount())) {
                log.warn("該 帳號account: \"{}\" 已被註冊", adminRequest.getAdminAccount());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
        return adminDao.createAdmin(adminRequest);
    }

    @Override
    public Admin getAdminById(Integer adminId) {
        return adminDao.getAdminById(adminId);
    }

    @Override
    public void deleteAdmin(Integer adminId) {
        adminDao.deleteAdmin(adminId);
    }

    @Override
    public void updateAdmin(Integer adminId, AdminRequest adminRequest) {
        List<Admin> adminList = adminDao.getAdmins();
        Admin adminById = adminDao.getAdminById(adminId);

        for (Admin admin : adminList){
            if (admin.getAdminAccount().equals(adminRequest.getAdminAccount())){
                if (adminById.getAdminAccount().equals(adminRequest.getAdminAccount())){
                    // 允許帳號不修改通過
                    System.out.println("帳號不變，通過");
                    break;
                }else {
                    log.warn("帳號 \"{}\" 已存在，請勿重複", adminRequest.getAdminAccount());
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
                }
            }
        }
        adminDao.updateAdmin(adminId, adminRequest);
    }
}
