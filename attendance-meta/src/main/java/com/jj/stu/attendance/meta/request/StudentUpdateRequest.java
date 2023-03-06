package com.jj.stu.attendance.meta.request;


import lombok.Data;

import java.util.Date;

/**
 * 学生更新请求
 *
 * @author zhangjunjie
 * @date 2023/03/06
 */
@Data
public class StudentUpdateRequest {

    private Integer id;

    /**
     * 学号
     */
    private String username;

    /**
     * 姓名
     */
    private String password;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 专业Id
     */
    private Integer clazzId;

    /**
     * 性别
     */
    private String sex;
    /**
     * 电话
     */
    private String mobile;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 关联用户id
     */
    private Integer adminId;
}