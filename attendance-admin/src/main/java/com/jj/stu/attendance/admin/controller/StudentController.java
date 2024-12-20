package com.jj.stu.attendance.admin.controller;

import com.jj.stu.attendance.admin.service.StudentService;
import com.jj.stu.attendance.admin.util.ValidateUtil;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.basic.StpUserDetail;
import com.jj.stu.attendance.base.constants.LogRecordType;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.meta.request.PageStudentRequest;
import com.jj.stu.attendance.meta.request.StudentBatchInsertRequest;
import com.jj.stu.attendance.meta.request.StudentInsertRequest;
import com.jj.stu.attendance.meta.request.StudentUpdateRequest;
import com.jj.stu.attendance.meta.response.PageStudentResponse;
import com.mzt.logapi.starter.annotation.LogRecord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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
    public Result<PageStudentResponse> pageStudentList(@RequestBody PageStudentRequest request) {
        ValidateUtil.validate(request);
        return ResultGenerator.getResultByOk(studentService.pageStudentList(request));
    }

    @LogRecord(
            fail = "批量添加学生信息失败，失败原因：「{{#_errorMsg}}」",
            subType = "MANAGER_VIEW",
            success = "{{#detail.userId}}批量添加学生信息「{{#request.studentList}}」成功,修改结果:{{#_ret}}",
            operator = "{{#detail.nickName}}", type = LogRecordType.STUDENT, bizNo = "batch_add_student")
    @PostMapping("/batch/add/student")
    @ApiOperation("批量添加学生")
    public void batchAddStudent(@RequestBody StudentBatchInsertRequest request, StpUserDetail detail) {
        request.setUsername(detail.getNickName());
        request.setUserId(detail.getUserId());
        ValidateUtil.validate(request);
        studentService.batchAddStudent(request);
    }

    @LogRecord(
            fail = "修改学生信息失败，失败原因：「{{#_errorMsg}}」",
            subType = "MANAGER_VIEW",
            success = "{{#detail.userId}}修改学生信息「{{#request.username}}」成功,修改结果:{{#_ret}}",
            operator = "{{#detail.nickName}}", type = LogRecordType.STUDENT, bizNo = "{{#request.id}}")
    @PostMapping("/update/student/info")
    @ApiOperation("修改学生信息")
    public Result<String> updateStudentInfo(@RequestBody StudentUpdateRequest request, StpUserDetail detail) {
        request.setCreateUserName(detail.getNickName());
        request.setCreateUserId(detail.getUserId());
        ValidateUtil.validate(request);
        studentService.updateStudentInfo(request);
        return ResultGenerator.getResultByOk("修改学生信息成功");
    }

    @LogRecord(
            fail = "批量刪除学生列表失败，失败原因：「{{#_errorMsg}}」",
            subType = "MANAGER_VIEW",
            success = "{{#detail.userId}}批量刪除学生列表「{{#studentIds}}」成功,修改结果:{{#_ret}}",
            operator = "{{#detail.nickName}}", type = LogRecordType.STUDENT, bizNo = "{{#studentIds}}")
    @ApiOperation("批量刪除学生列表")
    @PostMapping("/batch/delete/student/list")
    public Result<String> batchDeleteStudentList(@RequestBody List<Integer> studentIds, StpUserDetail detail) {
        if (CollectionUtils.isEmpty(studentIds)) {
            throw new ApiException("学生列表为空");
        }
        studentService.batchDeleteStudentList(studentIds);
        return ResultGenerator.getResultByOk("批量刪除学生列表成功");
    }


    @LogRecord(
            fail = "添加学生信息失败，失败原因：「{{#_errorMsg}}」",
            subType = "MANAGER_VIEW",
            success = "{{#detail.userId}}添加学生信息「{{#request.nickName}}」成功,修改结果:{{#_ret}}",
            operator = "{{#detail.nickName}}", type = LogRecordType.STUDENT, bizNo = "add_student")
    @PostMapping("/student/add")
    @ApiOperation("添加学生")
    public Result<Integer> addStudent(@RequestBody StudentInsertRequest request, StpUserDetail detail) {
        request.setCreateUserName(detail.getNickName());
        request.setCreateUserId(detail.getUserId());
        ValidateUtil.validate(request);
        return ResultGenerator.getResultByOk(studentService.addStudent(request));
    }
}
