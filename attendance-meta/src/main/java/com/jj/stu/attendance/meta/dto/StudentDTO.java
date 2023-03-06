package com.jj.stu.attendance.meta.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author zhangjunjie
 * @description
 */
@Data
public class StudentDTO {
    private Integer id;

    /**
     * 学号
     */
    private String username;

    /**
     * 姓名
     */
    private String password;

    private String nickName;

    /**
     * 专业Id
     */
    private Integer clazzId;

    private String sex;

    private String mobile;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 关联用户id
     */
    private Integer adminId;

    /**
     * ADMIN 消息封装
     */
    private AdminDTO adminDTO;
}
