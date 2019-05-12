package com.kakarot.service;

import com.kakarot.pojo.Book;

public interface BookService {
    Object getPage(int offset, int limit, Book book);
    Book getInfoById(Integer id);
    Object update(Book book);
    Object delete(int id);
    Object updateStatus(int id,int status);
}
