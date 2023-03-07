package com.jj.stu.attendance.meta.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 用户登录请求
 *
 * @author 张俊杰
 * @date 2023/02/13
 */
@Data
public class UserLoginRequest {

    @NotNull
    private String userName;

    @NotNull
    private String password;

}
