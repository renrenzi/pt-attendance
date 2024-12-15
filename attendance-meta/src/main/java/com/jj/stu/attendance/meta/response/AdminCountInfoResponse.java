package com.jj.stu.attendance.meta.response;

import lombok.Data;

@Data
public class AdminCountInfoResponse {
    /**
     * 评论数
     */
    private Integer info = 0;
    /**
     * 分类数
     */
    private Integer category = 0;
    /**
     * 友链数
     */
    private Integer comment = 0;
    ;
    /**
     * 标签数
     */
    private Integer tag = 0;
    ;
    /**
     * 访问量
     */
    private Integer lin = 0;
    ;
}
