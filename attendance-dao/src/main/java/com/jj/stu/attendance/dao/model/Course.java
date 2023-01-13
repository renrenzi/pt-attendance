package com.jj.stu.attendance.dao.model;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 课程表
 */
@Data
@TableName("s_course")
public class Course {
    private static final long serialVersionUID = 1L;
    private Integer id;

    /**
     * 课程名字
     */
    private String name;

    /**
     * 教师Id
     */
    private Integer teacherId;

    /**
     * 课程时间
     */
    private Date courseDate;

    /**
     * 选课人数
     */
    private Integer selectedNum;

    /**
     * 最大人数
     */
    private Integer maxNum;

    private String info;
}