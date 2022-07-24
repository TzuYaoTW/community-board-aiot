package com.tzuyao.communityboardaiot.dao;

import com.tzuyao.communityboardaiot.dto.PackageRequest;
import com.tzuyao.communityboardaiot.model.Package;

public interface PackageDao {

    Package getPackageById(Integer packageId);

    Integer createPackage(PackageRequest packageRequest, String state);

    void deletePackage(Integer packageId);

    void updatePackage(PackageRequest packageRequest, Integer packageId);
}
