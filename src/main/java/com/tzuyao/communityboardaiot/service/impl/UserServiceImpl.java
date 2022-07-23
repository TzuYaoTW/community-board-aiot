package com.tzuyao.communityboardaiot.service.impl;

import com.tzuyao.communityboardaiot.dao.UserDao;
import com.tzuyao.communityboardaiot.dto.UserQueryParams;
import com.tzuyao.communityboardaiot.dto.UserRequest;
import com.tzuyao.communityboardaiot.model.User;
import com.tzuyao.communityboardaiot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public List<User> getUsers(UserQueryParams userQueryParams) {
        return userDao.getUsers(userQueryParams);
    }

    @Override
    public Integer countUser(UserQueryParams userQueryParams) {
        return userDao.countUser(userQueryParams);
    }

    @Override
    public User getUserById(Integer userId) {
        return userDao.getUserById(userId);
    }

    @Override
    public Integer createUser(UserRequest userRequest) {
        return userDao.createUser(userRequest);
    }

    @Override
    public void updateUser(Integer userId, UserRequest userRequest) {
        userDao.updateUser(userId, userRequest);
    }

    @Override
    public void deleteUser(Integer userId) {
        userDao.deleteUser(userId);
    }
}
