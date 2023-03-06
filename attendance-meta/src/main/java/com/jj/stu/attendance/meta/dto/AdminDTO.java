package com.jj.stu.attendance.meta.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author zhangjunjie
 * @description
 */
@Data
public class AdminDTO {
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
}
