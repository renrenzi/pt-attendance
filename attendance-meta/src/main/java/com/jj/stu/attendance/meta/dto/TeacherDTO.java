package com.jj.stu.attendance.meta.dto;

import lombok.Data;

import java.util.Date;

@Data
public class TeacherDTO {
    private Integer id;

    /**
     * 账号
     */
    private String username;

    /**
     * 专业Id
     */
    private Integer clazzId;

    /**
     * 专业名称
     */
    private String clazzName;

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
     * 关联adminId
     */
    private Integer adminId;
}
