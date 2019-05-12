package com.kakarot.service.impl;

import com.alibaba.fastjson.JSON;
import com.kakarot.dao.BookDao;
import com.kakarot.dao.impl.BookDaoImpl;
import com.kakarot.pojo.Book;
import com.kakarot.service.BookService;
import com.kakarot.util.Constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookServiceImpl implements BookService {
    private BookDao bookDao=new BookDaoImpl();
    @Override
    public Object getPage(int offset, int limit, Book book) {
        List<Book> list=bookDao.getPage(offset,limit,book);
        int total=bookDao.getCount(book);
        Map<String,Object> map=new HashMap<>();
        map.put("total",total);
        map.put("rows",list);
        return JSON.toJSON(map);
    }

    @Override
    public Book getInfoById(Integer id) {
        return bookDao.getInfoById(id);
    }

    @Override
    public Object update(Book book) {
        int result;
        Map<String,Object> map=new HashMap<>();
        if(book.getId()==null){
            result=bookDao.insert(book);
        }else{
            result=bookDao.update(book);
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
    public Object delete(int id) {
        Map<String,Object> map=new HashMap<>();
        if(bookDao.delete(id)>0){
            map.put("code",Constant.SUCCESS);
            map.put("msg",Constant.SUCCESS_DELETE_MSG);
        }else{
            map.put("code",Constant.ERROR);
            map.put("msg",Constant.ERROR_DELETE_MSG);
        }
        return JSON.toJSON(map);
    }

    @Override
    public Object updateStatus(int id, int status) {
        Map<String,Object> map=new HashMap<>();
        if(bookDao.updateStatus(status,id)>0){
            map.put("code",Constant.SUCCESS);
            map.put("msg",Constant.SUCCESS_STATUS_MSG);
        }else{
            map.put("code",Constant.ERROR);
            map.put("msg",Constant.ERROR_STATUS_MSG);
        }
        return JSON.toJSON(map);
    }
}
