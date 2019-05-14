package com.kakarot.service.impl;

import com.alibaba.fastjson.JSON;
import com.kakarot.dao.BookDao;
import com.kakarot.dao.RecordDao;
import com.kakarot.dao.impl.BookDaoImpl;
import com.kakarot.dao.impl.RecordDaoImpl;
import com.kakarot.pojo.Book;
import com.kakarot.pojo.Record;
import com.kakarot.service.ViewService;
import com.kakarot.util.MyUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ViewServiceImpl implements ViewService {
    private BookDao bookDao=new BookDaoImpl();
    private RecordDao recordDao=new RecordDaoImpl();

    @Override
    public List<Book> getInfoByHot() {
        return bookDao.getInfoByHot();
    }

    @Override
    public List<Book> getAllBook() {
        return null;
    }

    @Override
    public List<Book> getInfoByAid(int aid) {
        return null;
    }

    @Override
    public List<Book> getInfoByPid(int pid) {
        return null;
    }

    @Override
    public Book getById(int id) {
        return null;
    }

    @Override
    public Object bBook(Record record) {
        String time=MyUtil.nowDate();
        record.setBorrowTime(time);
        Map<String,Object> map=new HashMap<>();
        if(recordDao.borrow(record)>0){
            if(bookDao.bBook(record.getBookid())>0){
                map.put("code",1);
                map.put("msg","借阅成功");
            }else{
                map.put("code",0);
                map.put("msg","借阅失败");
            }
        }else{
            map.put("code",0);
            map.put("msg","借阅失败");
        }
        return JSON.toJSON(map);
    }

    @Override
    public Object rBook(Record record) {
        return null;
    }
}
