package com.jj.stu.attendance.dao.request.student;

import com.jj.stu.attendance.dao.mapper.StudentMapper;
import com.jj.stu.attendance.dao.model.Student;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 页面学生请求
 *
 * @author 张俊杰
 * @date 2022/11/20
 */
@Data
public class PageStudentRequest {
    @NotNull
    private Integer pageNum;
    @NotNull
    private Integer pageSize;

    private Student student;
}
