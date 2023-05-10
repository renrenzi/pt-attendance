package com.jj.stu.attendance.base.basic;

import lombok.*;

/**
 * 登录用户详细信息
 *
 * @author 张俊杰
 * @date 2023/02/07
 */
@Data
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class StpUserDetail {

    /**
     * 账号id（唯一标识）
     */
    private Integer adminId;

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

    private String roleName;
}
