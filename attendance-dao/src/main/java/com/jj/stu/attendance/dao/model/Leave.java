package com.jj.stu.attendance.dao.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 请假表
 */
@Data
@TableName("s_leave")
@Accessors(chain = true)
public class Leave {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
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