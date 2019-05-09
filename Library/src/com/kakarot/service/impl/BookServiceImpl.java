package com.kakarot.service.impl;

import com.alibaba.fastjson.JSON;
import com.kakarot.dao.BookDao;
import com.kakarot.dao.impl.BookDaoImpl;
import com.kakarot.pojo.Book;
import com.kakarot.service.BookService;

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
}
