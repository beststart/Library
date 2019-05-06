package com.kakarot.dao;

import com.kakarot.pojo.UserInfo;

public interface UserDao {
    UserInfo login(UserInfo userInfo);
}
