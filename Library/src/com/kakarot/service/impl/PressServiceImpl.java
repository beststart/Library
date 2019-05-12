package com.kakarot.service.impl;

import com.alibaba.fastjson.JSON;
import com.kakarot.dao.PressDao;
import com.kakarot.dao.impl.PressDaoImpl;
import com.kakarot.pojo.Press;
import com.kakarot.service.PressService;
import com.kakarot.util.Constant;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PressServiceImpl implements PressService {
    private PressDao pressDao=new PressDaoImpl();

    @Override
    public Object getPage(int offset, int limit, Press press) {
        List<Press> list=pressDao.getPage(offset,limit,press);
        int total=pressDao.getCount(press);
        Map<String,Object> map=new HashMap<>();
        map.put("total",total);
        map.put("rows",list);
        return JSON.toJSON(map);
    }

    @Override
    public Press getInfoById(int id) {
        return pressDao.getInfoById(id);
    }

    @Override
    public Object update(Press press) {
        int result;
        Map<String,Object> map=new HashMap<>();
        if(press.getId()==0){
            result=pressDao.insert(press);
        }else{
            result=pressDao.update(press);
        }
        if(result>0){
            map.put("code",Constant.SUCCESS);
            map.put("msg","信息更新成功！");
        }else{
            map.put("code",Constant.ERROR);
            map.put("msg","信息更新失败！");
        }
        return JSON.toJSON(map);
    }

    @Override
    public Object delete(int id) {
        Map<String,Object> map=new HashMap<>();
        if(pressDao.delete(id)>0){
            map.put("code",Constant.SUCCESS);
            map.put("msg",Constant.SUCCESS_DELETE_MSG);
        }else{
            map.put("code",Constant.ERROR);
            map.put("msg",Constant.ERROR_DELETE_MSG);
        }
        return JSON.toJSON(map);
    }

    @Override
    public Object updateStatus(int id, int status) {
        Map<String,Object> map=new HashMap<>();
        if(pressDao.updateStatus(id,status)>0){
            map.put("code",Constant.SUCCESS);
            map.put("msg",Constant.SUCCESS_UPDATE_MSG);
        }else{
            map.put("code",Constant.ERROR);
            map.put("msg",Constant.ERROR_UPDATE_MSG);
        }
        return JSON.toJSON(map);
    }

    @Override
    public List<Press> getAll() {
        return pressDao.getAll();
    }
}
