package com.tzuyao.communityboardaiot.service.impl;

import com.tzuyao.communityboardaiot.dao.PackageDao;
import com.tzuyao.communityboardaiot.dto.PackageRequest;
import com.tzuyao.communityboardaiot.model.Package;
import com.tzuyao.communityboardaiot.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PackageServiceImpl implements PackageService {

    @Autowired
    private PackageDao packageDao;

    @Override
    public Package getPackageById(Integer packageId) {
        return packageDao.getPackageById(packageId);
    }

    @Override
    public Integer createPackage(PackageRequest packageRequest, String state) {
        return packageDao.createPackage(packageRequest, state);
    }

    @Override
    public void deletePackage(Integer packageId) {
        packageDao.deletePackage(packageId);
    }

    @Override
    public void updatePackage(PackageRequest packageRequest, Integer packageId) {
        packageDao.updatePackage(packageRequest, packageId);
    }
}
