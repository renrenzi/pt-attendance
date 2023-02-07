package com.jj.stu.attendance.base.basic;

import lombok.Data;

/**
 * 登录用户详细信息
 *
 * @author 张俊杰
 * @date 2023/02/07
 */
@Data
public class StpUserDetail {

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 昵称
     */
    private String nickName;

}
