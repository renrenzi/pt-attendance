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
 * 教师表
 */
@Data
@TableName("s_teacher")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
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