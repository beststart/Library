package com.kakarot.service;

import com.kakarot.pojo.Record;

public interface RecordService {
    Object getPage(int offset, int pageSize, Record record);
    Object returnBook(Record record);
}
