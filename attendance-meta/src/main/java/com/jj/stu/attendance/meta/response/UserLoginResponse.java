package com.jj.stu.attendance.meta.response;

import com.jj.stu.attendance.base.basic.StpUserDetail;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLoginResponse {

    /**
     * token
     */
    private String token;

    /**
     * 登录的用户详情
     */
    private StpUserDetail detail;
}
