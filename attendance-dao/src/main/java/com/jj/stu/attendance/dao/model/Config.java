package com.jj.stu.attendance.dao.model;

import java.util.Date;
import lombok.Data;

@Data
public class Config {
    /**
    * 字段名
    */
    private String configField;

    /**
    * 配置名
    */
    private String configName;

    /**
    * 配置项的值
    */
    private String configValue;

    /**
    * 创建时间
    */
    private Date createTime;

    /**
    * 修改时间
    */
    private Date updateTime;
}