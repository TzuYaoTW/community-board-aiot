package com.tzuyao.communityboardaiot.dao;

import com.tzuyao.communityboardaiot.dto.UserQueryParams;
import com.tzuyao.communityboardaiot.dto.UserRequest;
import com.tzuyao.communityboardaiot.model.User;

import java.util.List;

public interface UserDao {

    List<User> getUsers(UserQueryParams userQueryParams);

    Integer countUser(UserQueryParams userQueryParams);

    User getUserById(Integer userId);

    Integer createUser(UserRequest userRequest);

    void updateUser(Integer userId, UserRequest userRequest);

    void deleteUser(Integer userId);
}
