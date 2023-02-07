package com.jj.stu.attendance.meta.request;

import com.jj.stu.attendance.dao.model.SelectedCourse;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 管理选修课请求
 *
 * @author LENOVO
 * @date 2022/12/07
 */
@Data
public class ManageSelectedCourseRequest {

    private Integer id;
    /**
     * 学生Id
     */
    @NotNull
    private Integer studentId;

    /**
     * 课程Id
     */
    @NotNull
    private Integer courseId;

    /**
     * 旧课程id
     */
    private Integer oldCourseId;
}
