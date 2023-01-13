package com.jj.stu.attendance.dao.request;

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
    /**
     * 所选课程
     */
    @NotNull
    private SelectedCourse selectedCourse;
}
