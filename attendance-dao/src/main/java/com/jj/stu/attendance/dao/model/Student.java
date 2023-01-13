package com.jj.stu.attendance.dao.model;

import java.util.Date;
import lombok.Data;

/**
 * 学生表
 */
@Data
public class Student {
    private static final long serialVersionUID = 1L;
    private Integer id;

    /**
     * 学号
     */
    private Integer username;

    /**
     * 姓名
     */
    private String password;

    private String nickName;

    /**
     * 专业Id
     */
    private Integer clazzId;

    private String sex;

    private String mobile;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 关联用户id
     */
    private Integer adminId;
}