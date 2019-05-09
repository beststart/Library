package com.kakarot.dao.impl;

import com.kakarot.dao.BookDao;
import com.kakarot.pojo.Book;
import com.kakarot.util.BaseDao;

import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    @Override
    public List<Book> getPage(Integer offset, Integer limit, Book book) {
        List list=new ArrayList();
        StringBuilder sql=new StringBuilder("select b.*,a.name anme,p.name pname from book b,author a,press p where b.authorid=a.id and b.pressid=p.id");
        makeSql(sql,list,book);
        sql.append(" order by b.id desc limit ?,?");
        list.add(offset);
        list.add(limit);
        return BaseDao.baseQuery(sql.toString(),list,Book.class);
    }

    private void makeSql(StringBuilder sql,List list,Book book){

    }


    @Override
    public List<Book> getAll() {
        return null;
    }

    @Override
    public Integer getCount(Book book) {
        List list=new ArrayList();
        StringBuilder sql=new StringBuilder("select count(b.id) from book b,author a,press p where b.authorid=a.id and b.pressid=p.id");
        makeSql(sql,list,book);
        return BaseDao.getCount(sql.toString(),list);
    }

    @Override
    public Integer updateStatus(Integer status, Integer id) {
        return null;
    }

    @Override
    public Integer update(Book book) {
        return null;
    }

    @Override
    public Integer delete(Integer id) {
        return null;
    }

    @Override
    public Integer insert(Book book) {
        return null;
    }

    @Override
    public Book getInfoById(Integer id) {
        return null;
    }

    @Override
    public Book getInfoByAid(int aid) {
        return null;
    }

    @Override
    public Book getInfoByPid(int pid) {
        return null;
    }
}
