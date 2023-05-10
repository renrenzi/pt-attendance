package com.jj.stu.attendance.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.jj.stu.attendance.base.constants.StringConstants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 管理员表
 */
@Data
@TableName("s_admin")
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Admin {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
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

    /**
     * 获取 id + : + username
     */
    public String getIdJoinUsername() {
        return StringConstants.USER_INFO + ":" + id;
    }
}