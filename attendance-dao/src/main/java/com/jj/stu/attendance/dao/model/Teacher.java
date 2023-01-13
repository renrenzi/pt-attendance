package com.jj.stu.attendance.dao.model;

import java.util.Date;
import lombok.Data;

/**
 * 教师表
 */
@Data
public class Teacher {
    private static final long serialVersionUID = 1L;
    private Integer id;

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 专业
     */
    private Integer clazzId;

    /**
     * 性别
     */
    private String sex;

    private String mobile;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 关联adminId
     */
    private Integer adminId;
}