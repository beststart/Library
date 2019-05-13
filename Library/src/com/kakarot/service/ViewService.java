package com.kakarot.service;

import com.kakarot.pojo.Book;
import com.kakarot.pojo.Record;

import java.util.List;

public interface ViewService {
    List<Book> getInfoByHot();
    List<Book> getAllBook();
    List<Book> getInfoByAid(int aid);
    List<Book> getInfoByPid(int pid);
    Book getById(int id);
    Object bBook(Record record);
    Object rBook(Record record);
}
