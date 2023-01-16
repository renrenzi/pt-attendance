package com.jj.stu.attendance.meta.response;

import lombok.Data;

import java.util.Date;

@Data
public class PageAdminInfoResponse<T> {
    private Integer id;

    /**
     * 用户名
     */
    private String userName;
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

    /**
     * 用户详情
     */
    private T info;

    /**
     * 角色id
     */
    private Integer roleId;
}
