package com.jj.stu.attendance.meta.request;

import com.jj.stu.attendance.dao.model.Student;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author 任人子
 * @date 2022/11/4  - {TIME}
 */
@Data
public class StudentBatchInsertRequest {

    /**
     * 学生列表
     */
    @NotEmpty
   private List<Student> studentList;


    /**
     * 用户名
     */
    private String username;

    /**
     * 用户id
     */
    private Integer userId;
}