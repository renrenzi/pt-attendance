package com.jj.stu.attendance.meta.request;

import lombok.Data;

import java.util.Date;

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
