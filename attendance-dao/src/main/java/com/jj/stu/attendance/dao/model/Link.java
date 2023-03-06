package com.jj.stu.attendance.dao.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Link {
    /**
    * 友链表主键id
    */
    private Integer linkId;

    /**
    * 友链类别 0-友链 1-推荐 2-个人网站
    */
    private Integer linkType;

    /**
    * 网站名称
    */
    private String linkName;

    /**
    * 网站链接
    */
    private String linkUrl;

    /**
    * 网站描述
    */
    private String linkDescription;

    /**
    * 用于列表排序
    */
    private Integer linkRank;

    /**
    * 是否删除 0-未删除 1-已删除
    */
    private Integer isDeleted;

    /**
    * 添加时间
    */
    private Date createTime;
}