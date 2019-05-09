package com.kakarot.service;

import com.kakarot.pojo.Press;

public interface PressService {
    Object getPage(int offset, int limit, Press press);
    Press getInfoById(int id);
    Object update(Press press);
    Object delete(int id);
    Object updateStatus(int id, int status);
}
