package com.kakarot.dao;

import com.kakarot.pojo.Record;

import java.util.List;

public interface RecordDao {
    int borrow(Record record);
    List<Record> getPage(int offset,int pageSize,Record record);
    int getCount(Record record);
    int rBook(Record record);
}
