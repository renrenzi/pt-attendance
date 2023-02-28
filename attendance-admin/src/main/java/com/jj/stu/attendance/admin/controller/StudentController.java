package com.jj.stu.attendance.admin.controller;

import com.jj.stu.attendance.admin.service.StudentService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.admin.util.ValidateUtil;
import com.jj.stu.attendance.meta.request.PageStudentRequest;
import com.jj.stu.attendance.meta.request.StudentBatchInsertRequest;
import com.jj.stu.attendance.meta.response.PageStudentResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 学生控制器
 * 学生管理
 *
 * @author 任人子
 * @date 2022/11/4  - {TIME}
 */
@RequestMapping("/student")
@RestController
@Api(tags = "学生管理")
public class StudentController {

    @Resource
    private StudentService studentService;

    @ApiOperation("分页获取学生列表")
    @PostMapping("/page/student/list")
    public Result<PageStudentResponse> pageStudentList(@RequestBody PageStudentRequest request){
        ValidateUtil.validate(request);
        return ResultGenerator.getResultByOk(studentService.pageStudentList(request));
    }
    

    @PostMapping("/batch/add/student")
    @ApiOperation("批量添加学生")
    public void batchAddStudent(@RequestBody StudentBatchInsertRequest request){
        ValidateUtil.validate(request);
        studentService.batchAddStudent(request);
    }
}
