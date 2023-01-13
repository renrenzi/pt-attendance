package com.jj.stu.attendance.meta.request;

import com.jj.stu.attendance.dao.model.Teacher;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 页面请求老师
 *
 * @author 张俊杰
 * @date 2022/11/20
 */
@Data
public class PageTeacherRequest {
    @NotNull
    private Integer pageNum;
    @NotNull
    private Integer pageSize;

    private Teacher teacher;
}
