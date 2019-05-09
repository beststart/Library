package com.kakarot.service;

import com.kakarot.pojo.Book;

public interface BookService {
    Object getPage(int offset, int limit, Book book);
}
