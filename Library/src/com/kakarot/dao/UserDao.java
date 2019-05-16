package com.kakarot.dao;

import com.kakarot.pojo.UserInfo;

import java.util.List;

public interface UserDao {
    UserInfo login(UserInfo userInfo);
    List<UserInfo> getPage(Integer page,Integer pageSize,UserInfo userInfo);
    Integer getCount(UserInfo userInfo);
    Integer updateStatus(Integer status,Integer id);
    Integer update(UserInfo userInfo);
    Integer delete(Integer id);
    Integer updatePwd(String password,Integer id);
    Integer insert(UserInfo userInfo);
    UserInfo checkUserName(String usename);
    UserInfo checkPwd(String pwd,Integer id);
    UserInfo getInfoById(Integer id);
    List<UserInfo> getAll();
}
