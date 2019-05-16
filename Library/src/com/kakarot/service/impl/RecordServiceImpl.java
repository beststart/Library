package com.kakarot.service.impl;

import com.alibaba.fastjson.JSON;
import com.kakarot.dao.BookDao;
import com.kakarot.dao.RecordDao;
import com.kakarot.dao.impl.BookDaoImpl;
import com.kakarot.dao.impl.RecordDaoImpl;
import com.kakarot.pojo.Record;
import com.kakarot.service.RecordService;
import com.kakarot.util.Constant;
import com.kakarot.util.MyUtil;
import com.mysql.jdbc.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecordServiceImpl implements RecordService {
    private RecordDao recordDao=new RecordDaoImpl();
    private BookDao bookDao=new BookDaoImpl();
    @Override
    public Object getPage(int offset, int pageSize, Record record) {
        String bTime=record.getBorrowTime();
        String rTime=record.getReturnTime();
        if(!StringUtils.isNullOrEmpty(bTime)){
            String[] bt=bTime.split(" - ");
            record.setBstime(bt[0]);
            record.setBetime(bt[1]);
        }
        if(!StringUtils.isNullOrEmpty(rTime)){
            String[] rt=rTime.split(" - ");
            record.setRstime(rt[0]);
            record.setRetime(rt[1]);
        }

        List<Record> list=recordDao.getPage(offset,pageSize,record);
        int total=recordDao.getCount(record);
        Map<String,Object> map=new HashMap<>();
        map.put("total",total);
        map.put("rows",list);
        return JSON.toJSON(map);
    }

    @Override
    public Object returnBook(Record record) {
        record.setReturnTime(MyUtil.nowDate());
        Map<String,Object> map=new HashMap<>();
        if(recordDao.rBook(record)>0){
            if(bookDao.rBook(record.getBookid())>0){
                map.put("code",Constant.SUCCESS);
                map.put("msg","还书成功！");
            }else{
                map.put("code",Constant.ERROR);
                map.put("msg","还书失败！");
            }
        }else{
            map.put("code",Constant.ERROR);
            map.put("msg","还书记录更新失败！");
        }
        return JSON.toJSON(map);
    }
}
