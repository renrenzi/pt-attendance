package com.jj.stu.attendance.meta.request;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
public class PageAdminListRequest {


    /**
     * 页号
     */
    @NotNull
    private Integer pageNum;

    /**
     * 页数
     */
    @NotNull
    private Integer pageSize;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 角色id
     */
    private Integer roleId;

}
