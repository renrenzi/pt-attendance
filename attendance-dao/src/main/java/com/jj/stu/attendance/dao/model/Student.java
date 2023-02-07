package com.jj.stu.attendance.dao.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 学生表
 */
@Data
@TableName("s_student")
public class Student {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 学号
     */
    private String username;

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