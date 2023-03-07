package com.jj.stu.attendance.dao.model;

import java.util.Date;

import lombok.Data;

@Data
public class Task {
    /**
     *
     */
    private Integer id;

    /**
     * 任务名称
     */
    private String name;

    /**
     * 请求参数
     */
    private String param;

    /**
     * 0：上传；1：下载
     */
    private Byte type;

    /**
     * 请求URL
     */
    private String url;

    /**
     * 成功结果
     */
    private String result;

    /**
     * 错误信息
     */
    private String error;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建用户ID
     */
    private Integer createUserId;
}