package com.jj.stu.attendance.dao.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 选课表
 */
@Data
@TableName("s_selected_course")
@Builder
@NoArgsConstructor
@AllArgsConstructor
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