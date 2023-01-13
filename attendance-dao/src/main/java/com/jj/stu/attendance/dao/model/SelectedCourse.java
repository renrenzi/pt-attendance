package com.jj.stu.attendance.dao.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * 选课表
 */
@Data
@TableName("s_selected_course")
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