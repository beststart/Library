package com.kakarot.service;

import com.kakarot.pojo.UserInfo;

public interface UserService {
    UserInfo doLogin(String username,String password);
    Object getPage(Integer offset,Integer limit,UserInfo userInfo);
    Object updateStatus(Integer id,Integer status);
    Object delete(Integer id);
}
