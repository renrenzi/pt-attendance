package com.jj.stu.attendance.dao.mapper;

import com.jj.stu.attendance.dao.model.Config;

public interface ConfigMapper {
    int deleteByPrimaryKey(String configField);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(String configField);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);
}