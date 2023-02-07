package com.jj.stu.attendance.dao.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 专业表
 */
@Data
@TableName("s_clazz")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Clazz {
    private static final long serialVersionUID = 1L;
    /**
     * 主键id
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 专业名称
     */
    private String name;

    /**
     * 专业描述
     */
    private String info;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建用户id
     */
    private Integer createUserId;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改用户id
     */
    private Integer updateUserId;

    /**
     * 1->启用 0 ->禁用
     */
    private Integer state;
}