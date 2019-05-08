package com.kakarot.service;

import com.kakarot.pojo.UserInfo;

public interface UserService {
    UserInfo doLogin(String username,String password);
    Object getPage(Integer offset,Integer limit,UserInfo userInfo);
    Object updateStatus(Integer id,Integer status);
    Object delete(Integer id);
    Object checkUserName(String username);
    Object update(UserInfo userInfo);
    Object checkPwd(Integer id,String pwd);
    Object updatePwd(Integer id,String pwd);
    UserInfo getInfoById(Integer id);
}
