package com.kakarot.service;

import com.kakarot.pojo.UserInfo;

public interface UserService {
    UserInfo doLogin(String username,String password);
}
