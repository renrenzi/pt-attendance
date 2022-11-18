package com.jj.stu.attendance.dao.request.student;

import com.jj.stu.attendance.dao.model.Student;
import lombok.Data;
import lombok.NonNull;

import javax.validation.constraints.NotEmpty;
import java.util.List;

/**
 * @author 任人子
 * @date 2022/11/4  - {TIME}
 */
@Data
public class StudentBatchInsertRequest {
    @NotEmpty
    List<Student> studentList;
}