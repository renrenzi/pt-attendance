package com.jj.stu.attendance.meta.request;

import com.jj.stu.attendance.dao.model.Course;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * 编辑课程要求
 *
 * @author LENOVO
 * @date 2022/11/22
 */
@Data
public class EditCourseRequest{

    private Integer id;

    /**
     * 课程名字
     */
    @NotNull
    private String name;

    /**
     * 教师Id
     */
    @NotNull
    private Integer teacherId;

    /**
     * 课程时间
     */
    private Date courseDate;

    /**
     * 选课人数
     */
    @NotNull
    private Integer selectedNum;

    /**
     * 最大人数
     */
    @NotNull
    private Integer maxNum;

    private String info;
}
