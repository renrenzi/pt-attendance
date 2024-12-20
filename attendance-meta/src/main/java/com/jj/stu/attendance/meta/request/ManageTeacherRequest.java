package com.jj.stu.attendance.meta.request;

import lombok.Data;

/**
 * 管理教师请求
 *
 * @author 张俊杰
 * @date 2023/03/06
 */
@Data
public class ManageTeacherRequest {
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

    /**
     * 联系方式
     */
    private String mobile;

    /**
     * 更新用户id
     */
    private Integer updateUserId;

    private String updateUserName;
}
