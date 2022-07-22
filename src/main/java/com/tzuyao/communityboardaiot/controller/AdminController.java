package com.tzuyao.communityboardaiot.controller;

import com.tzuyao.communityboardaiot.model.Admin;
import com.tzuyao.communityboardaiot.service.AdminService;
import com.tzuyao.communityboardaiot.dto.AdminRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;


    // 查詢管理員列表
    @GetMapping("/admins")
    public ResponseEntity<List<Admin>> getAdmins(){
        List<Admin> adminList = adminService.getAdmins();
        return ResponseEntity.status(HttpStatus.OK).body(adminList);
    }

    // 透過adminId查詢管理員資料
    @GetMapping("/admins/{adminId}")
    public ResponseEntity<Admin> getAdminById(@PathVariable Integer adminId) {
        Admin admin = adminService.getAdminById(adminId);

        if (admin != null){
            return ResponseEntity.status(HttpStatus.OK).body(admin);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    // 新增管理員資料
    @PostMapping("/admins")
    public ResponseEntity<Admin> createAdmin(@RequestBody @Valid AdminRequest adminRequest){

        Integer adminId = adminService.createAdmin(adminRequest);

        Admin admin = adminService.getAdminById(adminId);

        return ResponseEntity.status(HttpStatus.OK).body(admin);
    }

    // 透過adminId刪除管理員資料
    @DeleteMapping("/admins/{adminId}")
    public ResponseEntity<?> deleteAdmin(@PathVariable Integer adminId){

        adminService.deleteAdminById(adminId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    // 透過adminId修改管理員資料
    @PutMapping("/admins/{adminId}")
    public ResponseEntity<Admin> updateAdmin(@PathVariable Integer adminId,
                                                 @RequestBody @Valid AdminRequest adminRequest){
        Admin admin = adminService.getAdminById(adminId);
        if (admin == null){
            System.out.println("測試, adminId:" + adminId + " 不存在");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        adminService.updateAdmin(adminId, adminRequest);

        Admin updateAdmin = adminService.getAdminById(adminId);

        return ResponseEntity.status(HttpStatus.OK).body(updateAdmin);
    }
}
