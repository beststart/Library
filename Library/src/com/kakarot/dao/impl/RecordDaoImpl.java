package com.kakarot.dao.impl;

import com.kakarot.dao.RecordDao;
import com.kakarot.pojo.Record;
import com.kakarot.util.BaseDao;
import com.mysql.jdbc.StringUtils;

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

    @Override
    public List<Record> getPage(int offset, int pageSize, Record record) {
        List list=new ArrayList();
        StringBuilder sql=new StringBuilder("select r.*,u.realname uname,b.name bname from record r,userinfo u,book b where r.userid=u.id and r.bookid=b.id");
        makeSql(sql,list,record);
        sql.append(" order by r.borrowTime desc limit ?,?");
        list.add(offset);
        list.add(pageSize);
        return BaseDao.baseQuery(sql.toString(),list,Record.class);
    }

    private void makeSql(StringBuilder sql, List list, Record record) {
        if(record.getBookid()!=null){
            sql.append(" and r.bookid=?");
            list.add(record.getBookid());
        }
        if(record.getUserid()!=null){
            sql.append(" and r.userid=?");
            list.add(record.getUserid());
        }
        if(!StringUtils.isNullOrEmpty(record.getBorrowTime())){
            sql.append(" and r.borrowTime between ? and ?");
            list.add(record.getBstime());
            list.add(record.getBetime());
        }
        if(!StringUtils.isNullOrEmpty(record.getReturnTime())){
            sql.append(" and r.returnTime between ? and ?");
            list.add(record.getRstime());
            list.add(record.getRetime());
        }
    }

    @Override
    public int getCount(Record record) {
        StringBuilder sql=new StringBuilder("select count(r.id) from record r,userinfo u,book b where r.userid=u.id and r.bookid=b.id");
        List list=new ArrayList();
        return BaseDao.getCount(sql.toString(),list);
    }

    @Override
    public int rBook(Record record) {
        String sql="update record set returnTime=? where id=?";
        List list=new ArrayList();
        list.add(record.getReturnTime());
        list.add(record.getId());
        return BaseDao.baseUpdate(sql,list);
    }
}
