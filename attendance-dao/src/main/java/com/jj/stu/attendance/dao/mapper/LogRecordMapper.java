package com.jj.stu.attendance.dao.mapper;

import com.jj.stu.attendance.dao.model.LogRecord;

public interface LogRecordMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(LogRecord record);

    int insertSelective(LogRecord record);

    LogRecord selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(LogRecord record);

    int updateByPrimaryKey(LogRecord record);
}