package com.kakarot.dao.impl;

import com.kakarot.dao.BookDao;
import com.kakarot.pojo.Book;
import com.kakarot.util.BaseDao;
import com.mysql.jdbc.StringUtils;
import com.sun.org.apache.regexp.internal.RE;

import java.util.ArrayList;
import java.util.List;

public class BookDaoImpl implements BookDao {
    @Override
    public List<Book> getPage(Integer offset, Integer limit, Book book) {
        List list=new ArrayList();
        StringBuilder sql=new StringBuilder("select b.*,a.name aname,p.name pname from book b,author a,press p where b.authorid=a.id and b.pressid=p.id");
        makeSql(sql,list,book);
        sql.append(" order by b.id desc limit ?,?");
        list.add(offset);
        list.add(limit);
        return BaseDao.baseQuery(sql.toString(),list,Book.class);
    }

    private void makeSql(StringBuilder sql,List list,Book book){
        if(!StringUtils.isNullOrEmpty(book.getName())){
            sql.append(" and b.name like ?");
            list.add("%"+book.getName()+"%");
        }
        if(book.getStatus()!=null){
            sql.append(" and b.status = ?");
            list.add(book.getStatus());
        }
        if(book.getAuthorid()!=null){
            sql.append(" and b.authorid= ?");
            list.add(book.getAuthorid());
        }
        if(book.getPressid()!=null){
            sql.append(" and b.pressid = ?");
            list.add(book.getPressid());
        }
    }


    @Override
    public List<Book> getAll() {
        String sql="select * from book where status = 1";
        return BaseDao.baseQuery(sql,Book.class);
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
        String sql="update book set status = ? where id = ?";
        List list=new ArrayList();
        list.add(status);
        list.add(id);
        return BaseDao.baseUpdate(sql,list);
    }

    @Override
    public Integer update(Book book) {
        String sql="update book set name=?,price=?,img=?,authorid=?,pressid=?,remake=?,intro=? where id = ?";
        List list=new ArrayList();
        list.add(book.getName());
        list.add(book.getPrice());
        list.add(book.getImg());
        list.add(book.getAuthorid());
        list.add(book.getPressid());
        list.add(book.getRemake());
        list.add(book.getIntro());
        list.add(book.getId());
        return BaseDao.baseUpdate(sql,list);
    }

    @Override
    public Integer delete(Integer id) {
        String sql="delete from book where id = ?";
        List list=new ArrayList();
        list.add(id);
        return BaseDao.baseUpdate(sql,list);
    }

    @Override
    public Integer insert(Book book) {
        String sql="insert into book values (null,?,?,?,?,?,?,?,1,0)";
        List list=new ArrayList();
        list.add(book.getName());
        list.add(book.getPrice());
        list.add(book.getImg());
        list.add(book.getAuthorid());
        list.add(book.getPressid());
        list.add(book.getRemake());
        list.add(book.getIntro());
        return BaseDao.baseUpdate(sql,list);
    }

    @Override
    public Book getInfoById(Integer id) {
        String sql="select * from book where id = ?";
        List list=new ArrayList();
        list.add(id);
        return BaseDao.baseQueryBean(sql,list,Book.class);
    }

    @Override
    public List<Book> getInfoByAid(int aid) {
        String sql="select * from book where authorid = ?";
        List list=new ArrayList();
        list.add(aid);
        return BaseDao.baseQuery(sql,list,Book.class);
    }

    @Override
    public List<Book> getInfoByPid(int pid) {
        String sql="select * from book where pressid = ?";
        List list=new ArrayList();
        list.add(pid);
        return BaseDao.baseQuery(sql,list,Book.class);
    }

    @Override
    public List<Book> getInfoByHot() {
        String sql="select b.*,a.name aname,p.name pname from book b,author a,press p where b.authorid=a.id and b.pressid=p.id and b.status=1 order by count desc limit 9";
        return BaseDao.baseQuery(sql,Book.class);
    }

    @Override
    public int bBook(Integer id) {
        String sql="update book set status=0,count=count+1 where id=?";
        List list=new ArrayList();
        list.add(id);
        return BaseDao.baseUpdate(sql,list);
    }

    @Override
    public int rBook(Integer id) {
        String sql="update book set status=1 where id = ?";
        List list=new ArrayList();
        list.add(id);
        return BaseDao.baseUpdate(sql,list);
    }

    @Override
    public List<Book> getAllBook() {
        String sql="select * from book";
        return BaseDao.baseQuery(sql,Book.class);
    }
}
