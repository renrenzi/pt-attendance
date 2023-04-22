package com.jj.stu.attendance.meta.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class EditAdminInfoRequest {

    @NotNull
    private Integer adminId;

    @NotBlank
    private String password;

    @NotNull
    private Integer updateUserId;

    @NotNull
    private String updateUserName;
}
