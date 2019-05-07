package com.kakarot.dao.impl;

import com.kakarot.dao.PowerDao;
import com.kakarot.pojo.Power;
import com.kakarot.util.BaseDao;

import java.util.List;

public class PowerDaoImpl implements PowerDao {
    @Override
    public List<Power> getAll() {
        String sql="select * from power where status=1";
        return BaseDao.baseQuery(sql,Power.class);
    }
}
