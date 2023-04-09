package com.jj.stu.attendance.admin.service.impl;

import com.jj.stu.attendance.dao.mapper.LogRecordMapper;
import com.mzt.logapi.beans.LogRecord;
import com.mzt.logapi.service.ILogRecordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
@Service
@Slf4j
public class LogRecordServiceImpl implements ILogRecordService {

    @Resource
    private LogRecordMapper logRecordMapper;

    @Override
    public void record(LogRecord logRecord) {
        log.info("【logRecord】log={}", logRecord);
        com.jj.stu.attendance.dao.model.LogRecord record = com.jj.stu.attendance.dao.model.LogRecord.builder()
                .action(logRecord.getAction())
                .extra(logRecord.getExtra())
                .bizNo(logRecord.getBizNo())
                .operator(logRecord.getOperator())
                .tenant(logRecord.getTenant())
                .subType(logRecord.getSubType())
                .type(logRecord.getType())
                .createDate(logRecord.getCreateTime())
                .build();
        // 将日志持久化到数据库当中
        logRecordMapper.insert(record);
    }

    @Override
    public List<LogRecord> queryLog(String bizNo, String type) {
        return new ArrayList<>();
    }

    @Override
    public List<LogRecord> queryLogByBizNo(String bizNo, String type, String subType) {
        return new ArrayList<>();
    }
}
