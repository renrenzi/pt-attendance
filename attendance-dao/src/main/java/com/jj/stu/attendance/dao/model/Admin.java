package com.jj.stu.attendance.dao.model;

import java.util.Date;
import lombok.Data;

/**
 * 管理员表
 */
@Data
public class Admin {
    private static final long serialVersionUID = 1L;
    private Integer id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 密码
     */
    private String passWord;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private Date updateUser;

    /**
     * 是否有效
     */
    private Integer state;
}