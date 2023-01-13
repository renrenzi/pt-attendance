package com.jj.stu.attendance.dao.model;

import lombok.Data;

/**
 * 选课表
 */
@Data
public class SelectedCourse {
    private static final long serialVersionUID = 1L;
    private Integer id;

    /**
     * 学生Id
     */
    private Integer studentId;

    /**
     * 课程Id
     */
    private Integer courseId;
}