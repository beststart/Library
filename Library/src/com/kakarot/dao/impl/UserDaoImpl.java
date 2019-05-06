package com.kakarot.dao.impl;

import com.kakarot.dao.UserDao;
import com.kakarot.pojo.UserInfo;
import com.kakarot.util.BaseDao;

import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    @Override
    public UserInfo login(UserInfo userInfo) {
        String sql="select * from userinfo where username=? and password=?";
        List<Object> pList=new ArrayList<>();
        pList.add(userInfo.getUsername());
        pList.add(userInfo.getPassword());
        return BaseDao.baseQueryBean(sql,pList,UserInfo.class);
    }
}
