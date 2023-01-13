package com.jj.stu.attendance.dao.request;

import com.jj.stu.attendance.dao.model.Course;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 编辑课程要求
 *
 * @author LENOVO
 * @date 2022/11/22
 */
@Data
public class EditCourseRequest{

    @NotNull
    private Course course;
}
