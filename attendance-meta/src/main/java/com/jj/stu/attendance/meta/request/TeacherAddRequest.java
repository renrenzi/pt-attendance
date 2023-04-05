package com.jj.stu.attendance.meta.request;

import lombok.Data;

@Data
public class TeacherAddRequest {

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

    /**
     * 联系方式
     */
    private String mobile;

    /**
     * 创建用户id
     */
    private Integer createUserId;

    /**
     * 创建用户名
     */
    private String createUserName;
}
