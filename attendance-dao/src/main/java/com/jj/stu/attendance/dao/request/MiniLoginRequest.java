package com.jj.stu.attendance.dao.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class MiniLoginRequest {

    @NotNull
    private String userName;

    @NotNull
    private String password;

    @NotNull
    private String roleName;
}
