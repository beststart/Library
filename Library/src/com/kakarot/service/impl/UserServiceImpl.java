package com.kakarot.service.impl;

import com.kakarot.dao.UserDao;
import com.kakarot.dao.impl.UserDaoImpl;
import com.kakarot.pojo.UserInfo;
import com.kakarot.service.UserService;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();

    @Override
    public UserInfo doLogin(String username, String password) {
        UserInfo userInfo=new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(password);
        return userDao.login(userInfo);
    }
}
