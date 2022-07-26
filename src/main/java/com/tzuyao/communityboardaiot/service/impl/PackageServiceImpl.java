package com.tzuyao.communityboardaiot.service.impl;

import com.tzuyao.communityboardaiot.dao.PackageDao;
import com.tzuyao.communityboardaiot.dao.RfidDao;
import com.tzuyao.communityboardaiot.dto.PackageQueryParams;
import com.tzuyao.communityboardaiot.dto.PackageRequest;
import com.tzuyao.communityboardaiot.model.Package;
import com.tzuyao.communityboardaiot.service.PackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PackageServiceImpl implements PackageService {

    @Autowired
    private PackageDao packageDao;

    @Autowired
    private RfidDao rfidDao;


    @Override
    public List<Package> getPackages(PackageQueryParams packageQueryParams) {
        //如果你有給rfid的話，透過rfid_code抓到地址並且覆寫packageQueryParams的地址
        if (packageQueryParams.getSearchByRfid() != null) {
            String userAddress = rfidDao.getAddressByRfidCode(packageQueryParams.getSearchByRfid());
            packageQueryParams.setSearchByAddress(userAddress);
            packageQueryParams.setSearchByRfid(null);
            System.out.println(packageQueryParams.getSearchByRfid());
        }
        return packageDao.getPackages(packageQueryParams);
    }

    @Override
    public Integer countPackage(PackageQueryParams packageQueryParams) {
        return packageDao.countPackage(packageQueryParams);
    }

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
    public void updatePackage(PackageRequest packageRequest, Integer packageId, String state) {
        packageDao.updatePackage(packageRequest, packageId, state);
    }
}
