package com.tzuyao.communityboardaiot.controller;

import com.tzuyao.communityboardaiot.constant.FacilityCategory;
import com.tzuyao.communityboardaiot.dto.FacilityQueryParams;
import com.tzuyao.communityboardaiot.dto.FacilityRequest;
import com.tzuyao.communityboardaiot.model.Facility;
import com.tzuyao.communityboardaiot.service.FacilityService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Validated
@RestController
public class FacilityController {

    @Autowired
    private FacilityService facilityService;

    @Operation(summary = "取得設施預定列表")
    @GetMapping("/facilities")
    public ResponseEntity<List<Facility>> getFacilities(@RequestParam(required = false) String name,
                                                        @RequestParam(required = false) FacilityCategory category,
                                                        @RequestParam(required = false) String day,
                                                        @RequestParam(required = false) String time,
                                                        @RequestParam(required = false) String today,
                                                        @RequestParam(defaultValue = "reserved_day") String orderBy,
                                                        @RequestParam(defaultValue = "ASC") String sort){
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
    @GetMapping("/facilities/{facilityId}")
    public ResponseEntity<Facility> getFacilityById(@PathVariable Integer facilityId) {
        Facility facility = facilityService.getFacilityById(facilityId);
        return ResponseEntity.status(HttpStatus.OK).body(facility);
    }

    @Operation(summary = "新增預約設施", description = "新增預約設施")
    @PostMapping("/facilities")
    public ResponseEntity<Facility> getFacilities(@RequestBody @Valid FacilityRequest facilityRequest) {
        Integer facilityId = facilityService.createFacility(facilityRequest);
        Facility facility = facilityService.getFacilityById(facilityId);
        return ResponseEntity.status(HttpStatus.OK).body(facility);
    }

    @Operation(summary = "修改預約", description = "透過facility_id取得設施預定資料後修改")
    @PutMapping("/facilities/{facilityId}")
    public ResponseEntity<Facility> updateFacility(@PathVariable Integer facilityId,
                                                   @RequestBody FacilityRequest facilityRequest){
        Facility facility = facilityService.getFacilityById(facilityId);
        if (facility == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        facilityService.updateFacility(facilityId, facilityRequest);
        Facility updateFacility = facilityService.getFacilityById(facilityId);

        return ResponseEntity.status(HttpStatus.OK).body(updateFacility);
    }

    @Operation(summary = "刪除預約", description = "透過facility_id刪除預約")
    @DeleteMapping("/facilities/{facilityId}")
    public ResponseEntity<Facility> deleteFacility(@PathVariable Integer facilityId) {
        facilityService.deleteFacility(facilityId);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}

