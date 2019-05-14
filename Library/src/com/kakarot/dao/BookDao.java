package com.kakarot.dao;


import com.kakarot.pojo.Book;

import java.util.List;

public interface BookDao {
    List<Book> getPage(Integer offset, Integer limit, Book book);
    List<Book> getAll();
    Integer getCount(Book book);
    Integer updateStatus(Integer status, Integer id);
    Integer update(Book book);
    Integer delete(Integer id);
    Integer insert(Book book);
    Book getInfoById(Integer id);
    List<Book> getInfoByAid(int aid);
    List<Book> getInfoByPid(int pid);
    List<Book> getInfoByHot();
    int bBook(Integer id);
}
