package com.kakarot.service;

import com.kakarot.pojo.Author;

public interface AuthorService {
    Object getPage(int offset, int limit, Author author);
    Author getInfoById(int id);
    Object update(Author author);
    Object delete(int id);
    Object updateStatus(int id,int status);
}
