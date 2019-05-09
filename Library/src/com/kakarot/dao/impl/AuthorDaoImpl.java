package com.kakarot.dao.impl;

import com.kakarot.dao.AuthorDao;
import com.kakarot.pojo.Author;
import com.kakarot.util.BaseDao;
import com.mysql.jdbc.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class AuthorDaoImpl implements AuthorDao {
    @Override
    public List<Author> getPage(int offset, int limit, Author author) {
        List list=new ArrayList();
        StringBuilder sql=new StringBuilder("select * from author where 1=1");
        makeSql(sql,list,author);
        sql.append(" order by id desc limit ?,?");
        list.add(offset);
        list.add(limit);
        return BaseDao.baseQuery(sql.toString(),list,Author.class);
    }

    private void makeSql(StringBuilder sql,List list,Author author){
        if(!StringUtils.isNullOrEmpty(author.getName())){
            sql.append(" and name like ?");
            list.add("%"+author.getName()+"%");
        }
        if(author.getStatus()!=null){
            sql.append(" and status=?");
            list.add(author.getStatus());
        }
    }

    @Override
    public Author getInfoById(int id) {
        String sql="select * from author where id = ?";
        List list=new ArrayList();
        list.add(id);
        return BaseDao.baseQueryBean(sql,list,Author.class);
    }

    @Override
    public int insert(Author author) {
        String sql="insert into author values(null,?,1,?)";
        List list=new ArrayList();
        list.add(author.getName());
        list.add(author.getRemake());
        return BaseDao.baseUpdate(sql,list);
    }

    @Override
    public int update(Author author) {
        String sql="update author set name=?,remake=?";
        List list=new ArrayList();
        list.add(author.getName());
        list.add(author.getRemake());
        return BaseDao.baseUpdate(sql,list);
    }

    @Override
    public int delete(int id) {
        String sql="delete from author where id = ?";
        List list=new ArrayList();
        list.add(id);
        return BaseDao.baseUpdate(sql,list);
    }

    @Override
    public List<Author> getAll() {
        String sql="select * from author where status = 1";
        return BaseDao.baseQuery(sql,Author.class);
    }

    @Override
    public int getCount(Author author) {
        List list=new ArrayList();
        StringBuilder sql=new StringBuilder("select count(*) count from author where 1=1");
        makeSql(sql,list,author);
        return BaseDao.getCount(sql.toString(),list);
    }

    @Override
    public int updateStatus(int id, int status) {
        String sql="update author set status = ? where id = ?";
        List list=new ArrayList();
        list.add(status);
        list.add(id);
        return BaseDao.baseUpdate(sql,list);
    }
}
