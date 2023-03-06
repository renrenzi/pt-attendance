package com.jj.stu.attendance.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jj.stu.attendance.dao.model.Config;

public interface ConfigMapper extends BaseMapper<Config> {
    int deleteByPrimaryKey(String configField);

    int insert(Config record);

    int insertSelective(Config record);

    Config selectByPrimaryKey(String configField);

    int updateByPrimaryKeySelective(Config record);

    int updateByPrimaryKey(Config record);
}