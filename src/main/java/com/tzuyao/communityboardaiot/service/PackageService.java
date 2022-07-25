package com.tzuyao.communityboardaiot.service;

import com.tzuyao.communityboardaiot.dto.PackageQueryParams;
import com.tzuyao.communityboardaiot.dto.PackageRequest;
import com.tzuyao.communityboardaiot.model.Package;

import java.util.List;

public interface PackageService {

    List<Package> getPackages(PackageQueryParams packageQueryParams);

    Integer countPackage(PackageQueryParams packageQueryParams);

    Package getPackageById(Integer packageId);

    Integer createPackage(PackageRequest packageRequest, String state);

    void deletePackage(Integer packageId);

    void updatePackage(PackageRequest packageRequest, Integer packageId, String state);
}
