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
 * 课程表
 */
@Data
@TableName("s_course")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO)
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