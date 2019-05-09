package com.kakarot.service.impl;

import com.alibaba.fastjson.JSON;
import com.kakarot.dao.AuthorDao;
import com.kakarot.dao.UserDao;
import com.kakarot.dao.impl.AuthorDaoImpl;
import com.kakarot.pojo.Author;
import com.kakarot.service.AuthorService;
import com.kakarot.util.Constant;

import javax.jws.Oneway;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthorServiceImpl implements AuthorService {
    private AuthorDao authorDao=new AuthorDaoImpl();

    @Override
    public Object getPage(int offset, int limit, Author author) {
        List<Author> list=authorDao.getPage(offset,limit,author);
        int total=authorDao.getCount(author);
        Map<String,Object> map=new HashMap<>();
        map.put("total",total);
        map.put("rows",list);
        return JSON.toJSON(map);
    }

    @Override
    public Author getInfoById(int id) {
        return authorDao.getInfoById(id);
    }

    @Override
    public Object update(Author author) {
        int result;
        Map<String,Object> map=new HashMap<>();
        if(author.getId()==0){
            result=authorDao.insert(author);
        }else{
            result=authorDao.update(author);
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
        if(authorDao.delete(id)>0){
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
        if(authorDao.updateStatus(id,status)>0){
            map.put("code",Constant.SUCCESS);
            map.put("msg",Constant.SUCCESS_UPDATE_MSG);
        }else{
            map.put("code",Constant.ERROR);
            map.put("msg",Constant.ERROR_UPDATE_MSG);
        }
        return JSON.toJSON(map);
    }
}
