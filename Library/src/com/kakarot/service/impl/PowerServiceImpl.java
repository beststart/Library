package com.kakarot.service.impl;

import com.kakarot.dao.PowerDao;
import com.kakarot.dao.impl.PowerDaoImpl;
import com.kakarot.pojo.Power;
import com.kakarot.service.PowerService;

import java.util.List;

public class PowerServiceImpl implements PowerService {
    private PowerDao powerDao=new PowerDaoImpl();
    @Override
    public List<Power> getAll() {
        return powerDao.getAll();
    }
}
