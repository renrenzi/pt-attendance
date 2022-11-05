package com.jj.stu.attendance.admin.comtroller;

import com.jj.stu.attendance.admin.service.StudentService;
import com.jj.stu.attendance.dao.request.StudentBatchInsertRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * 学生管理
 * @author 任人子
 * @date 2022/11/4  - {TIME}
 */
@RequestMapping("/student")
@RestController
public class StudentController {

    @Resource
    private StudentService studentService;

    @PostMapping("/batch/add/student")
    public void batchAddStudent(StudentBatchInsertRequest request){
        studentService.batchAddStudent(request);
    }
}
