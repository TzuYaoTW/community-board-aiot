package com.tzuyao.communityboardaiot.controller;

import com.tzuyao.communityboardaiot.dto.PackageQueryParams;
import com.tzuyao.communityboardaiot.dto.PackageRequest;
import com.tzuyao.communityboardaiot.model.Package;
import com.tzuyao.communityboardaiot.service.PackageService;
import com.tzuyao.communityboardaiot.util.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.List;

@Tag(name = "住戶包裹 CRUD", description = "管理員可以新增查詢修改刪除關於包裹的資料")
@Validated
@RestController
public class PackageController {

    @Autowired
    private PackageService packageService;

    @Operation(summary = "查詢包裹列表(模糊查詢地址+區隔領取與未領取+分頁+排序)", description = "透過模糊查詢地址取得包裹資料列表；" +
            "透過RFID CODE來抓取地址(一個住址可能有多個RFID，所以我只要使用其中一個RFID，就可以查到自己與同住家人的包裹；)，" +
            "可選擇查詢領取或未領取(預設未領取)；" +
            "依照前端請求orderBy指定項目排序(預設為預定日期時間)；" +
            "依照前端請求sort指定排序順序(預設DESC由新到舊排序)；" +
            "依照前端請求limit限制每頁筆數(預設5)；" +
            "依照前端請求offset設定跳過筆數(預設0，代表第一頁)；" +
            "回傳總數count讓前端計算分頁。")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功")
    })
    @GetMapping("/packages")
    public ResponseEntity<Page<Package>> getPackages(@RequestParam(required = false) @Parameter(description = "住戶地址") String address,
                                                     @RequestParam(required = false) @Parameter(description = "住戶RFID CODE(當使用此方法時，會在業務邏輯強制把address設為null)") String rfid,
                                                     @RequestParam(defaultValue = "0") @Parameter(description = "包裹狀態(未領取/已領取)") String state,
                                                     @RequestParam(defaultValue = "last_modified_date") @Parameter(description = "排序對象") String orderBy,
                                                     @RequestParam(defaultValue = "DESC") @Parameter(description = "排序順序") String sort,
                                                     @RequestParam(defaultValue = "5") @Parameter(description = "限制資料筆數") @Max(20) @Min(0) Integer limit,
                                                     @RequestParam(defaultValue = "0") @Parameter(description = "跳過資料筆數") @Min(0) Integer offset) {
        PackageQueryParams packageQueryParams = new PackageQueryParams();
        packageQueryParams.setSearchByAddress(address);
        packageQueryParams.setSearchByRfid(rfid);
        packageQueryParams.setState(state);
        packageQueryParams.setOrderBy(orderBy);
        packageQueryParams.setSort(sort);
        packageQueryParams.setLimit(limit);
        packageQueryParams.setOffset(offset);

        List<Package> packageList = packageService.getPackages(packageQueryParams);
        Integer total = packageService.countPackage(packageQueryParams);

        Page<Package> page= new Page<>();
        page.setLimit(limit);
        page.setOffset(offset);
        page.setTotal(total);
        page.setResults(packageList);

        return ResponseEntity.status(HttpStatus.OK).body(page);
    }

    @Operation(summary = "取得包裹資料", description = "透過package_id取得包裹資料並回傳前端")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功"),
            @ApiResponse(responseCode = "404", description = "無此 包裹 ID，無法執行查詢功能", content = {
                    @Content()
            })
    })
    @GetMapping("/packages/{packageId}")
    public ResponseEntity<Package> getPackageById(@PathVariable @Parameter(description = "包裹id") Integer packageId) {
        Package aPackage = packageService.getPackageById(packageId);
        if (aPackage == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }else {
            return ResponseEntity.status(HttpStatus.OK).body(aPackage);
        }
    }

    @Operation(summary = "新增包裹", description = "新增包裹資料")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功")
    })
    @PostMapping("/packages")
    public ResponseEntity<Package> createPackage(@RequestBody @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "請求參數列表") PackageRequest packageRequest,
                                                 @RequestParam(defaultValue = "0") @Parameter(description = "包裹狀態(已領取/未領取)") String state) {
        Integer packageId = packageService.createPackage(packageRequest, state);
        Package updatePackage = packageService.getPackageById(packageId);

        return ResponseEntity.status(HttpStatus.OK).body(updatePackage);
    }

    @Operation(summary = "修改包裹資料(修改包裹為已領取狀態)", description = "" +
            "透過 package_id 修改包裹資料，" +
            "透過RequestParam String state調整包裹狀態(即領取包裹)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功"),
            @ApiResponse(responseCode = "404", description = "無此 包裹 ID，無法執行修改功能", content = {
                    @Content()
            })
    })
    @PutMapping("/packages/{packageId}")
    public ResponseEntity<Package> updatePackage(@PathVariable @Parameter(description = "包裹id") Integer packageId,
                                                 @RequestParam(defaultValue = "0") @Parameter(description = "包裹狀態(已領取/未領取)") String state,
                                                 @RequestBody @Valid @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "請求參數列表") PackageRequest packageRequest) {
        Package aPackage = packageService.getPackageById(packageId);
        if (aPackage == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        packageService.updatePackage(packageRequest, packageId, state);
        Package updatePackage = packageService.getPackageById(packageId);
        return ResponseEntity.status(HttpStatus.OK).body(updatePackage);
    }

    @Operation(summary = "刪除包裹", description = "刪除包裹資料")
    @DeleteMapping("/packages/{packageId}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功",content = {
                    @Content()
            })
    })
    public ResponseEntity<Package> deletePackage(@PathVariable @Parameter(description = "包裹id") Integer packageId) {
        packageService.deletePackage(packageId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
