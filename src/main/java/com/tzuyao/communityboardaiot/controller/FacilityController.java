package com.tzuyao.communityboardaiot.controller;

import com.tzuyao.communityboardaiot.constant.FacilityCategory;
import com.tzuyao.communityboardaiot.dto.FacilityQueryParams;
import com.tzuyao.communityboardaiot.dto.FacilityRequest;
import com.tzuyao.communityboardaiot.model.Admin;
import com.tzuyao.communityboardaiot.model.Facility;
import com.tzuyao.communityboardaiot.model.Rfid;
import com.tzuyao.communityboardaiot.service.FacilityService;
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
import java.time.LocalDate;
import java.util.List;

@Tag(name = "公共設施租借 CRUD", description = "管理員可以新增查詢修改刪除關於公共設施的資料")
@Validated
@RestController
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    @Operation(summary = "取得設施預定列表", description = "可模糊查詢姓名，" +
            "可選擇場地，" +
            "可選擇日期，" +
            "可選擇時段，" +
            "可以排除掉已過期資料(也可以全部都顯示)，" +
            "依照前端請求orderBy指定項目排序(預設為預定日期時間)，" +
            "依照前端請求sort指定排序順序(預設ASC由小到大排序)，")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Facility.class))
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "無此 RFID ID，無法執行修改功能", content = {
                    @Content()
            })
    })
    @GetMapping("/facilities")
    public ResponseEntity<List<Facility>> getFacilities(@RequestParam(required = false)
                                                            @Parameter(description = "租借者") String name,
                                                        @RequestParam(required = false)
                                                        @Parameter(description = "場地") FacilityCategory category,
                                                        @RequestParam(required = false)
                                                            @Parameter(description = "租借日期") String day,
                                                        @RequestParam(required = false)
                                                            @Parameter(description = "租借時間") String time,
                                                        @RequestParam(required = false)
                                                            @Parameter(description = "可設定排除時間點") String today,
                                                        @RequestParam(defaultValue = "reserved_day")
                                                            @Parameter(description = "根據所選欄位排序") String orderBy,
                                                        @RequestParam(defaultValue = "ASC")
                                                            @Parameter(description = "排序方式") String sort){
        FacilityQueryParams facilityQueryParams = new FacilityQueryParams();
        facilityQueryParams.setUserName(name);
        facilityQueryParams.setFacilityCategory(category);
        if(day != null) {
            facilityQueryParams.setReservedDay(LocalDate.parse(day));
        }
        facilityQueryParams.setReservedTime(time);
        facilityQueryParams.setToday(today);
        facilityQueryParams.setOrderBy(orderBy);
        facilityQueryParams.setSort(sort);

        List<Facility> facilityList = facilityService.getFacilities(facilityQueryParams);

        return ResponseEntity.status(HttpStatus.OK).body(facilityList);
    }

    @Operation(summary = "取得設施預定情況", description = "透過facility_id取得設施預定資料")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Facility.class))
                            )
                    })
    })
    @GetMapping("/facilities/{facilityId}")
    public ResponseEntity<Facility> getFacilityById(@PathVariable Integer facilityId) {
        Facility facility = facilityService.getFacilityById(facilityId);
        return ResponseEntity.status(HttpStatus.OK).body(facility);
    }

    @Operation(summary = "新增預約設施", description = "新增預約設施")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Facility.class))
                            )
                    })
    })
    @PostMapping("/facilities")
    public ResponseEntity<Facility> getFacilities(@RequestBody @Valid FacilityRequest facilityRequest) {
        Integer facilityId = facilityService.createFacility(facilityRequest);
        Facility facility = facilityService.getFacilityById(facilityId);
        return ResponseEntity.status(HttpStatus.OK).body(facility);
    }

    @Operation(summary = "修改預約", description = "透過facility_id取得設施預定資料後修改")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功",
                    content = {
                            @Content(
                                    mediaType = "application/json",
                                    array = @ArraySchema(schema = @Schema(implementation = Facility.class))
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "無此 設施 ID，無法執行修改功能", content = {
                    @Content()
            })
    })
    @PutMapping("/facilities/{facilityId}")
    public ResponseEntity<Facility> updateFacility(@PathVariable Integer facilityId,
                                                   @RequestBody @Valid FacilityRequest facilityRequest){
        Facility facility = facilityService.getFacilityById(facilityId);
        if (facility == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        facilityService.updateFacility(facilityId, facilityRequest);
        Facility updateFacility = facilityService.getFacilityById(facilityId);

        return ResponseEntity.status(HttpStatus.OK).body(updateFacility);
    }

    @Operation(summary = "刪除預約", description = "透過facility_id刪除預約")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "成功",
                    content = {
                            @Content()
                    })
    })
    @DeleteMapping("/facilities/{facilityId}")
    public ResponseEntity<Facility> deleteFacility(@PathVariable Integer facilityId) {
        facilityService.deleteFacility(facilityId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

