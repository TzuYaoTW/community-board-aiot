package com.tzuyao.communityboardaiot.dao.impl;

import com.tzuyao.communityboardaiot.dao.RfidDao;
import com.tzuyao.communityboardaiot.dto.RfidQueryParams;
import com.tzuyao.communityboardaiot.dto.RfidRequest;
import com.tzuyao.communityboardaiot.model.Rfid;
import com.tzuyao.communityboardaiot.rowmapper.RfidRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class RfidDaoImpl implements RfidDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public Rfid getRfidById(Integer rfidId) {
        String sql = "SELECT rfid_id, user_address, rfid_name, rfid_code, " +
                "created_date, last_modified_date FROM rfid " +
                "WHERE rfid_id = :rfidId";
        Map<String, Object> map = new HashMap<>();
        map.put("rfidId", rfidId);

        List<Rfid> rfidList = namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource(map), new RfidRowMapper());

        if (rfidList.size() > 0) {
            return rfidList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public Integer createRfid(RfidRequest rfidRequest) {
        String sql = "INSERT INTO rfid(user_address, rfid_name, rfid_code, created_date, last_modified_date) " +
                "VALUES (:userAddress, :rfidName, :rfidCode, :createdDate, :lastModifiedDate)";
        Map<String, Object> map = new HashMap<>();
        map.put("userAddress", rfidRequest.getUserAddress());
        map.put("rfidName", rfidRequest.getRfidName());
        map.put("rfidCode", rfidRequest.getRfidCode());
        map.put("createdDate", new Date());
        map.put("lastModifiedDate", new Date());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        Integer rfidId = keyHolder.getKey().intValue();

        return rfidId;
    }



    @Override
    public void deleteRfid(Integer rfidId) {
        String sql = "DELETE FROM rfid WHERE rfid_id = :rfidId";
        Map<String, Object> map = new HashMap<>();
        map.put("rfidId", rfidId);
        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void updateRfid(RfidRequest rfidRequest, Integer rfidId) {
        String sql = "UPDATE rfid SET user_address = :userAddress, rfid_name = :rfidName, " +
                "rfid_code = :rfidCode, last_modified_date = :lastModifiedDate WHERE rfid_id = :rfidId";

        Map<String, Object> map = new HashMap<>();
        map.put("userAddress", rfidRequest.getUserAddress());
        map.put("rfidName", rfidRequest.getRfidName());
        map.put("rfidCode", rfidRequest.getRfidCode());
        map.put("lastModifiedDate", new Date());
        map.put("rfidId", rfidId);
        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public List<Rfid> getRfidList(RfidQueryParams rfidQueryParams) {
        String sql = "SELECT rfid_id, user_address, rfid_name, rfid_code, " +
                "created_date, last_modified_date FROM rfid WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        if (rfidQueryParams.getSearchByAddress() != null) {
            sql = sql + " AND user_address LIKE :userAddress";
            map.put("userAddress", "%" + rfidQueryParams.getSearchByAddress() + "%");
        }
        if (rfidQueryParams.getSearchByRfidCode() != null) {
            sql = sql + " AND rfid_code LIKE :rfidCode";
            map.put("rfidCode", "%" + rfidQueryParams.getSearchByRfidCode() + "%");
        }

        List<Rfid> rfidList = namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource(map), new RfidRowMapper());

        return rfidList;
    }
}
