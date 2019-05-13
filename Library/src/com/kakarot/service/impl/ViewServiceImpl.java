package com.kakarot.service.impl;

import com.kakarot.dao.BookDao;
import com.kakarot.dao.impl.BookDaoImpl;
import com.kakarot.pojo.Book;
import com.kakarot.pojo.Record;
import com.kakarot.service.ViewService;

import java.util.List;

public class ViewServiceImpl implements ViewService {
    private BookDao bookDao=new BookDaoImpl();

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
        return null;
    }

    @Override
    public Object rBook(Record record) {
        return null;
    }
}
