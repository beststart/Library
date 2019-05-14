package com.kakarot.dao.impl;

import com.kakarot.dao.RecordDao;
import com.kakarot.pojo.Record;
import com.kakarot.util.BaseDao;

import java.util.ArrayList;
import java.util.List;

public class RecordDaoImpl implements RecordDao {
    @Override
    public int borrow(Record record) {
        String sql="insert into record values(null,?,?,?,null)";
        List list=new ArrayList();
        list.add(record.getUserid());
        list.add(record.getBookid());
        list.add(record.getBorrowTime());
        return BaseDao.baseUpdate(sql,list);
    }
}
