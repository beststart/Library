package com.kakarot.service.impl;

import com.alibaba.fastjson.JSON;
import com.kakarot.dao.UserDao;
import com.kakarot.dao.impl.UserDaoImpl;
import com.kakarot.pojo.UserInfo;
import com.kakarot.service.UserService;
import com.kakarot.util.Constant;
import com.kakarot.util.MD5Util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private UserDao userDao=new UserDaoImpl();

    @Override
    public UserInfo doLogin(String username, String password) {
        UserInfo userInfo=new UserInfo();
        userInfo.setUsername(username);
        userInfo.setPassword(MD5Util.MD5(password));
        return userDao.login(userInfo);
    }

    @Override
    public Object getPage(Integer offset, Integer limit, UserInfo userInfo) {
        Map<String,Object> map=new HashMap<>();
        List<UserInfo> userList=userDao.getPage(offset,limit,userInfo);
        Integer count=userDao.getCount(userInfo);
        map.put("rows",userList);
        map.put("total",count);
        return JSON.toJSON(map);
    }

    @Override
    public Object updateStatus(Integer id, Integer status) {
        int result=userDao.updateStatus(status,id);
        Map<String,Object> map=new HashMap<>();
        if(result>0){
            map.put("code",Constant.SUCCESS);
            map.put("msg",Constant.SUCCESS_STATUS_MSG);
        }else{
            map.put("code",Constant.ERROR);
            map.put("msg",Constant.ERROR_STATUS_MSG);
        }
        return JSON.toJSON(map);
    }

    @Override
    public Object delete(Integer id) {
        Map<String,Object> map=new HashMap<>();
        if(userDao.delete(id)>0){
            map.put("code",Constant.SUCCESS);
            map.put("msg",Constant.SUCCESS_DELETE_MSG);
        }else{
            map.put("code",Constant.ERROR_STATUS_MSG);
            map.put("msg",Constant.ERROR_DELETE_MSG);
        }
        return JSON.toJSON(map);
    }

    @Override
    public Object checkUserName(String username) {
        Map<String,Boolean> map=new HashMap<>();
        UserInfo userInfo=userDao.checkUserName(username);
        if(userInfo==null){
            map.put("valid",true);
        }else{
            map.put("valid",false);
        }
        return JSON.toJSON(map);
    }

    @Override
    public Object update(UserInfo userInfo) {
        Map<String,Object> map=new HashMap<>();
        Integer result;
        if(userInfo.getId()!=0){
            result=userDao.update(userInfo);
        }else{
            result=userDao.insert(userInfo);
        }
        if(result>0){
            map.put("code",Constant.SUCCESS);
            map.put("msg","信息更新成功！");
        }else{
            map.put("code",Constant.ERROR);
            map.put("msg","信息更新失败！");
        }
        return JSON.toJSON(map);
    }

    @Override
    public Object checkPwd(Integer id, String pwd) {
        Map<String,Boolean> map=new HashMap<>();
        UserInfo userInfo=userDao.checkPwd(MD5Util.MD5(pwd),id);
        if(userInfo!=null){
            map.put("valid",true);
        }else{
            map.put("valid",false);
        }
        return JSON.toJSON(map);
    }

    @Override
    public Object updatePwd(Integer id, String pwd) {
        Map<String,Object> map=new HashMap<>();
        if(userDao.updatePwd(MD5Util.MD5(pwd),id)>0){
            map.put("code",Constant.SUCCESS);
            map.put("msg","密码更新成功！");
        }else{
            map.put("code",Constant.ERROR);
            map.put("msg","密码更新失败！");
        }
        return JSON.toJSON(map);
    }

    @Override
    public UserInfo getInfoById(Integer id) {
        return userDao.getInfoById(id);
    }
}
