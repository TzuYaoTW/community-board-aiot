package com.tzuyao.communityboardaiot.dao.impl;

import com.tzuyao.communityboardaiot.dao.FacialRecognitionDao;
import com.tzuyao.communityboardaiot.model.User;
import com.tzuyao.communityboardaiot.rowmapper.UserRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class FacialRecognitionDaoImpl implements FacialRecognitionDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public User getUserByFaceId(String faceId) {

        String sql = "SELECT user_id, user_face_id, user_name, " +
                "user_tel, user_address, created_date, " +
                "last_modified_date FROM `user` WHERE user_face_id = :faceId";

        Map<String, Object> map = new HashMap<>();
        map.put("faceId", faceId);

        List<User> userList = namedParameterJdbcTemplate.query(sql, new MapSqlParameterSource(map), new UserRowMapper());

        if (userList.size() > 0) {
            return userList.get(0);
        }else {
            return null;
        }
    }
}
