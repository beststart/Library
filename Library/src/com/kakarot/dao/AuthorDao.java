package com.kakarot.dao;

import com.kakarot.pojo.Author;

import java.util.List;

public interface AuthorDao {
    List<Author> getPage(int offset,int limit,Author author);
    Author getInfoById(int id);
    int insert(Author author);
    int update(Author author);
    int delete(int id);
    List<Author> getAll();
    int getCount(Author author);
    int updateStatus(int id,int status);
}
