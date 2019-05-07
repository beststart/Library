package com.kakarot.dao.impl;

import com.kakarot.dao.UserDao;
import com.kakarot.pojo.UserInfo;
import com.kakarot.util.BaseDao;
import com.mysql.jdbc.StringUtils;

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

    @Override
    public List<UserInfo> getPage(Integer page, Integer pageSize, UserInfo userInfo) {
        List list=new ArrayList();
        StringBuilder sql=new StringBuilder();
        sql.append("select u.*,p.power from userinfo u,power p where u.powerid=p.id");
        sqlFormate(sql,userInfo,list);
        sql.append(" limit ?,?");
        list.add(page);
        list.add(pageSize);
        return BaseDao.baseQuery(sql.toString(),list,UserInfo.class);
    }

    private void sqlFormate(StringBuilder sql,UserInfo userInfo,List list){
        if(userInfo!=null){
            if(!StringUtils.isNullOrEmpty(userInfo.getRealname())){
                sql.append(" and u.realname like ?");
                list.add("%"+userInfo.getRealname()+"%");
            }
            if(!StringUtils.isNullOrEmpty(userInfo.getPhone())){
                sql.append(" and u.phone=?");
                list.add(userInfo.getPhone());
            }
            if(userInfo.getPowerid()!=null){
                sql.append(" and u.powerid=?");
                list.add(userInfo.getPowerid());
            }
        }
    }

    @Override
    public Integer getCount(UserInfo userInfo) {
        List list=new ArrayList();
        StringBuilder sql=new StringBuilder();
        sql.append("select count(u.id) count from userinfo u,power p where u.powerid=p.id");
        sqlFormate(sql,userInfo,list);
        return BaseDao.getCount(sql.toString(),list);
    }

    @Override
    public Integer updateStatus(Integer status,Integer id) {
        String sql="update userinfo set status=? where id=?";
        List list=new ArrayList();
        list.add(status);
        list.add(id);
        return BaseDao.baseUpdate(sql,list);
    }

    @Override
    public Integer update(UserInfo userInfo) {
        String sql="update userinfo set realname=?,phone=?,age=? where id=?";
        List list=new ArrayList();
        list.add(userInfo.getRealname());
        list.add(userInfo.getPhone());
        list.add(userInfo.getAge());
        list.add(userInfo.getId());
        return BaseDao.baseUpdate(sql,list);
    }

    @Override
    public Integer delete(Integer id) {
        String sql="delete from userinfo where id=?";
        List list=new ArrayList();
        list.add(id);
        return BaseDao.baseUpdate(sql,list);
    }

    @Override
    public Integer updatePwd(String password, Integer id) {
        String sql="update userinfo set password=? where id=?";
        List list=new ArrayList();
        list.add(password);
        list.add(id);
        return BaseDao.baseUpdate(sql,list);
    }

    @Override
    public Integer insert(UserInfo userInfo) {
        String sql="insert into userinfo values(null,?,?,?,?,?,1,1)";
        List list=new ArrayList();
        list.add(userInfo.getUsername());
        list.add(userInfo.getPassword());
        list.add(userInfo.getRealname());
        list.add(userInfo.getAge());
        list.add(userInfo.getPhone());
        return BaseDao.baseUpdate(sql,list);
    }

    @Override
    public UserInfo checkUserName(String usename) {
        String sql="select * from userinfo where username=?";
        List list=new ArrayList();
        list.add(usename);
        return BaseDao.baseQueryBean(sql,list,UserInfo.class);
    }
}
