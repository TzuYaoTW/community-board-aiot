package com.tzuyao.communityboardaiot.dao.impl;

import com.tzuyao.communityboardaiot.dao.UserDao;
import com.tzuyao.communityboardaiot.dto.UserQueryParams;
import com.tzuyao.communityboardaiot.dto.UserRequest;
import com.tzuyao.communityboardaiot.model.User;
import com.tzuyao.communityboardaiot.rowmapper.UserRowMapper;
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
public class UserDaoImpl implements UserDao {

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @Override
    public List<User> getUsers(UserQueryParams userQueryParams) {

        String sql = "SELECT user_id, user_face_id, user_name, user_tel, user_address," +
                " created_date, last_modified_date FROM user WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        if (userQueryParams.getSearch() != null){
            sql = sql + " AND user_name LIKE :search";
            map.put("search", "%" + userQueryParams.getSearch() + "%");
        }
        sql = sql + " ORDER BY " + userQueryParams.getOrderBy() + " " + userQueryParams.getSort();

        sql = sql + " LIMIT :limit OFFSET :offset ";
        map.put("limit", userQueryParams.getLimit());
        map.put("offset", userQueryParams.getOffset());

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        return userList;
    }

    @Override
    public Integer countUser(UserQueryParams userQueryParams) {

        String sql = "SELECT COUNT(*) FROM user WHERE 1=1";

        Map<String, Object> map = new HashMap<>();

        if (userQueryParams.getSearch() != null){
            sql = sql + " AND user_name LIKE :search";
            map.put("search", "%" + userQueryParams.getSearch() + "%");
        }

        Integer total = namedParameterJdbcTemplate.queryForObject(sql, map, Integer.class);

        return total;
    }

    @Override
    public User getUserById(Integer userId) {

        String sql = "SELECT user_id, user_face_id, user_name, user_tel, user_address, created_date," +
                " last_modified_date FROM user WHERE user_id = :userId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        List<User> userList = namedParameterJdbcTemplate.query(sql, map, new UserRowMapper());

        if (userList.size() > 0) {
            return userList.get(0);
        }else {
            return null;
        }
    }

    @Override
    public Integer createUser(UserRequest userRequest) {

        String sql = "INSERT INTO user(user_face_id, user_name, user_tel, " +
                "user_address, created_date, last_modified_date) " +
                "VALUES (:userFaceId, :userName, :userTel, :userAddress, " +
                ":createdDate, :lastModifiedDate);";

        Map<String, Object> map = new HashMap<>();
        map.put("userFaceId", userRequest.getUserFaceId());
        map.put("userName", userRequest.getUserName());
        map.put("userTel", userRequest.getUserTel());
        map.put("userAddress", userRequest.getUserAddress());
        map.put("createdDate", new Date());
        map.put("lastModifiedDate", new Date());

        KeyHolder keyHolder = new GeneratedKeyHolder();

        namedParameterJdbcTemplate.update(sql, new MapSqlParameterSource(map), keyHolder);

        int userId = keyHolder.getKey().intValue();

        return userId;
    }

    @Override
    public void updateUser(Integer userId, UserRequest userRequest) {
        String sql = "UPDATE user SET user_face_id = :userFaceId, user_name = :userName, " +
                "user_t el = :userTel, user_address = :userAddress, " +
                "last_modified_date = :lastModifiedDate WHERE user_id = :userId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userFaceId", userRequest.getUserFaceId());
        map.put("userName", userRequest.getUserName());
        map.put("userTel", userRequest.getUserTel());
        map.put("userAddress", userRequest.getUserAddress());
        map.put("lastModifiedDate", new Date());
        map.put("userId", userId);

        namedParameterJdbcTemplate.update(sql, map);
    }

    @Override
    public void deleteUser(Integer userId) {
        String sql = "DELETE FROM user WHERE user_id = :userId;";

        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);

        namedParameterJdbcTemplate.update(sql, map);
    }

}
