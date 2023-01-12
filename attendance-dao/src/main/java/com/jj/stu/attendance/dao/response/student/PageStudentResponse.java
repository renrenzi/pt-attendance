package com.jj.stu.attendance.dao.response.student;


import com.jj.stu.attendance.dao.model.Student;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 页面学生响应
 *
 * @author 张俊杰
 * @date 2022/11/20
 */
@Data
@Accessors(chain = true)
public class PageStudentResponse {

    /**
     * 总大小
     */
    private Long totalSize;

    /**
     * 学生列表
     */
    private List<Student> studentList;
}
