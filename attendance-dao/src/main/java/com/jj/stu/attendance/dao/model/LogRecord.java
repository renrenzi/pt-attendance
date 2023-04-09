package com.jj.stu.attendance.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
@TableName("s_log_record")
public class LogRecord {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private String tenant;

    private String type;

    private String subType;

    private String bizNo;

    private String operator;

    private String action;

    private String fail;

    private String extra;

    private Date createDate;
}