package com.tzuyao.communityboardaiot.controller;

import com.tzuyao.communityboardaiot.dto.AdminRequest;
import com.tzuyao.communityboardaiot.model.Admin;
import com.tzuyao.communityboardaiot.service.AdminService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@OpenAPIDefinition(info = @Info(title = "iBoard 社區公佈欄(後端Spring boot)", version = "1.0.0"))
@Tag(name = "管理員 CRUD")
@RestController
public class AdminController {

    @Autowired
    private AdminService adminService;


    // 查詢管理員列表
    @Operation(summary = "取得所有管理員", description = "取得所有管理員資料")
    @GetMapping("/admins")
    public ResponseEntity<List<Admin>> getAdmins() {
        List<Admin> adminList = adminService.getAdmins();
        return ResponseEntity.status(HttpStatus.OK).body(adminList);
    }

    @Operation(summary = "透過adminId查詢管理員資料", description = "透過adminId查詢管理員資料")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Admin.class))
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "無此管理員 ID ", content = {
                    @Content()
            })
    })
    @GetMapping("/admins/{adminId}")
    public ResponseEntity<Admin> getAdminById(@Parameter(description = "管理員 ID 編號") @PathVariable Integer adminId) {
        Admin admin = adminService.getAdminById(adminId);

        if (admin != null) {
            return ResponseEntity.status(HttpStatus.OK).body(admin);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @Operation(summary = "新增管理員資料", description = "新增管理員資料")
    @PostMapping("/admins")
    public ResponseEntity<Admin> createAdmin(@RequestBody @Valid AdminRequest adminRequest) {

        Integer adminId = adminService.createAdmin(adminRequest);

        Admin admin = adminService.getAdminById(adminId);

        return ResponseEntity.status(HttpStatus.OK).body(admin);
    }

    @Operation(summary = "刪除管理員資料", description = "透過adminId刪除管理員資料")
    @DeleteMapping("/admins/{adminId}")
    public ResponseEntity<?> deleteAdmin(@Parameter(description = "管理員 ID 編號") @PathVariable Integer adminId) {

        adminService.deleteAdmin(adminId);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "修改管理員資料", description = "透過adminId先查詢是否存在，如存在該筆數據，執行修改；最後再使用adminId重新查詢該筆數據後回傳")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {
                            @Content(
                                    mediaType = "application/json"
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "無此管理員 ID", content = {
                    @Content()
            })
    })
    @PutMapping("/admins/{adminId}")
    public ResponseEntity<Admin> updateAdmin(@Parameter(description = "管理員 ID 編號") @PathVariable Integer adminId,
                                             @RequestBody @Valid AdminRequest adminRequest) {
        Admin admin = adminService.getAdminById(adminId);
        if (admin == null) {
            System.out.println("測試, adminId:" + adminId + " 不存在");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        adminService.updateAdmin(adminId, adminRequest);

        Admin updateAdmin = adminService.getAdminById(adminId);

        return ResponseEntity.status(HttpStatus.OK).body(updateAdmin);
    }
}
