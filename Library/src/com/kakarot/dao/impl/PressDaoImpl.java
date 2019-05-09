package com.kakarot.dao.impl;

import com.kakarot.dao.PressDao;
import com.kakarot.pojo.Press;
import com.kakarot.util.BaseDao;
import com.mysql.jdbc.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class PressDaoImpl implements PressDao {
    @Override
    public List<Press> getPage(Integer offset, Integer limit, Press press) {
        List list=new ArrayList();
        StringBuilder sql=new StringBuilder("select * from press where 1=1");
        makeSql(sql,list,press);
        sql.append(" order by id desc limit ?,?");
        list.add(offset);
        list.add(limit);
        return BaseDao.baseQuery(sql.toString(),list,Press.class);
    }

    private void makeSql(StringBuilder sql,List list,Press press){
        if(!StringUtils.isNullOrEmpty(press.getName())){
            sql.append(" and name like ?");
            list.add("%"+press.getName()+"%");
        }
        if(press.getStatus()!=null){
            sql.append(" and status = ?");
            list.add(press.getStatus());
        }
    }

    @Override
    public List<Press> getAll() {
        String sql="select * from press where status=1";
        return BaseDao.baseQuery(sql,Press.class);
    }

    @Override
    public Integer getCount(Press press) {
        List list=new ArrayList();
        StringBuilder sql=new StringBuilder("select count(id) from press where 1=1");
        makeSql(sql,list,press);
        return BaseDao.getCount(sql.toString(),list);
    }

    @Override
    public Integer updateStatus(Integer id, Integer status) {
        String sql="update press set status = ? where id = ?";
        List list=new ArrayList();
        list.add(status);
        list.add(id);
        return BaseDao.baseUpdate(sql,list);
    }

    @Override
    public Integer update(Press press) {
        String sql="update press set name = ?,phone=?,contact=?,address=?,remake=? where id =?";
        List list=new ArrayList();
        list.add(press.getName());
        list.add(press.getPhone());
        list.add(press.getContact());
        list.add(press.getAddress());
        list.add(press.getRemake());
        list.add(press.getId());
        return BaseDao.baseUpdate(sql,list);
    }

    @Override
    public Integer delete(Integer id) {
        String sql="delete from press where id = ?";
        List list=new ArrayList();
        list.add(id);
        return BaseDao.baseUpdate(sql,list);
    }

    @Override
    public Integer insert(Press press) {
        String sql="insert into press values(null,?,?,?,?,1,?)";
        List list=new ArrayList();
        list.add(press.getName());
        list.add(press.getPhone());
        list.add(press.getContact());
        list.add(press.getAddress());
        list.add(press.getRemake());
        list.add(press.getId());
        return BaseDao.baseUpdate(sql,list);
    }

    @Override
    public Press getInfoById(Integer id) {
        String sql="select * from press where id = ?";
        List list=new ArrayList();
        list.add(id);
        return BaseDao.baseQueryBean(sql,list,Press.class);
    }
}
