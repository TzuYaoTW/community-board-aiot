package com.tzuyao.communityboardaiot.controller;

import com.tzuyao.communityboardaiot.dto.PackageRequest;
import com.tzuyao.communityboardaiot.model.Admin;
import com.tzuyao.communityboardaiot.model.Package;
import com.tzuyao.communityboardaiot.model.User;
import com.tzuyao.communityboardaiot.service.PackageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class PackageController {

    @Autowired
    private PackageService packageService;

    @Operation(summary = "取得包裹資料", description = "透過package_id取得包裹資料並回傳前端")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Package.class))
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "無此 包裹 ID，無法執行查詢功能", content = {
                    @Content()
            })
    })
    @GetMapping("/packages/{packageId}")
    public ResponseEntity<Package> getPackageById(@PathVariable Integer packageId) {
        Package aPackage = packageService.getPackageById(packageId);
        if (aPackage == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(aPackage);
        }
    }

    @Operation(summary = "新增包裹", description = "新增包裹資料")
    @PostMapping("/packages")
    public ResponseEntity<Package> createPackage(@RequestBody @Valid PackageRequest packageRequest,
                                                 @RequestParam(defaultValue = "0") String state) {
        Integer packageId = packageService.createPackage(packageRequest, state);
        Package updatePackage = packageService.getPackageById(packageId);

        return ResponseEntity.status(HttpStatus.OK).body(updatePackage);
    }

    @Operation(summary = "修改包裹資料", description = "透過 package_id 修改包裹資料")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Package.class))
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "無此 包裹 ID，無法執行修改功能", content = {
                    @Content()
            })
    })
    @PutMapping("/packages/{packageId}")
    public ResponseEntity<Package> updatePakcage(@PathVariable Integer packageId,
                                                 @RequestBody @Valid PackageRequest packageRequest) {
        Package aPackage = packageService.getPackageById(packageId);
        if (aPackage == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        packageService.updatePackage(packageRequest, packageId);
        Package updatePackage = packageService.getPackageById(packageId);
        return ResponseEntity.status(HttpStatus.OK).body(updatePackage);
    }

    @Operation(summary = "刪除包裹", description = "刪除包裹資料")
    @DeleteMapping("/packages/{packageId}")
    public ResponseEntity<Package> deletePackage(@PathVariable Integer packageId) {
        packageService.deletePackage(packageId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
