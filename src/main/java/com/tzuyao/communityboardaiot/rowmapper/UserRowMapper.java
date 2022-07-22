package com.tzuyao.communityboardaiot.rowmapper;

import com.tzuyao.communityboardaiot.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {

        User user = new User();

        user.setUserId(resultSet.getInt("user_id"));
        user.setUserFaceId(resultSet.getString("user_face_id"));
        user.setUserName(resultSet.getString("user_name"));
        user.setUserTel(resultSet.getString("user_tel"));
        user.setUserAddress(resultSet.getString("user_address"));
        user.setCreatedDate(resultSet.getTimestamp("created_date"));
        user.setLastModifiedDate(resultSet.getTimestamp("last_modified_date"));

        return user;
    }
}
