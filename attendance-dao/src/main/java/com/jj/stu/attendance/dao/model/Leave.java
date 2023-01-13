package com.jj.stu.attendance.dao.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 请假表
 */
@Data
@TableName("s_leave")
public class Leave {
    private static final long serialVersionUID = 1L;
    private Integer id;

    /**
     * 学生Id
     */
    private Integer studentId;

    private String info;

    /**
     * 请假状态
     */
    private Boolean status;

    private String remark;

    /**
     * 创建时间
     */
    private Date createDate;
}