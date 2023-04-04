package com.jj.stu.attendance.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 学生表
 */
@Data
@TableName("s_student")
@Builder
@AllArgsConstructor
@NoArgsConstructor
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

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 专业Id
     */
    private Integer clazzId;

    /**
     * 性别
     */
    private String sex;

    /**
     * 联系方式
     */
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