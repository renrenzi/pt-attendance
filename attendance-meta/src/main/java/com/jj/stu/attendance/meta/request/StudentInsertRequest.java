package com.jj.stu.attendance.meta.request;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
public class StudentInsertRequest {

    /**
     * 学号
     */
    @NotNull
    @Length(max = 9)
    private String username;

    /**
     * 密码
     */
    @NotNull
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 专业Id
     */
    @NotNull
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
     * 创建用户名
     */
    private String createUserName;

    /**
     * 创建用户id
     */
    private Integer createUserId;
}
