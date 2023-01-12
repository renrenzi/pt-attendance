package com.jj.stu.attendance.dao.response.teacher;

import com.jj.stu.attendance.dao.model.Teacher;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 页面老师反应
 *
 * @author 张俊杰
 * @date 2022/11/20
 */
@Data
@Accessors(chain = true)
public class PageTeacherResponse {

    /**
     * 总大小
     */
    private Long totalSize;

    private List<Teacher> teacherList;
}
