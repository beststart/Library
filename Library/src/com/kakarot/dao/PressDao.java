package com.kakarot.dao;


import com.kakarot.pojo.Press;

import java.util.List;

public interface PressDao {
    List<Press> getPage(Integer offset, Integer limit, Press press);
    List<Press> getAll();
    Integer getCount(Press press);
    Integer updateStatus(Integer status, Integer id);
    Integer update(Press press);
    Integer delete(Integer id);
    Integer insert(Press press);
    Press getInfoById(Integer id);
}
