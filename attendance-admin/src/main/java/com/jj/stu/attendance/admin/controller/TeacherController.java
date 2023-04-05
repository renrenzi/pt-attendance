package com.jj.stu.attendance.admin.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.jj.stu.attendance.admin.service.TeacherService;
import com.jj.stu.attendance.admin.util.ValidateUtil;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.basic.StpUserDetail;
import com.jj.stu.attendance.base.constants.LogRecordType;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.meta.request.ManageTeacherRequest;
import com.jj.stu.attendance.meta.request.PageTeacherRequest;
import com.jj.stu.attendance.meta.request.TeacherAddRequest;
import com.jj.stu.attendance.meta.response.PageTeacherResponse;
import com.mzt.logapi.starter.annotation.LogRecord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * 老师控制器
 *
 * @author 张俊杰
 * @date 2022/11/20
 */
@Api(tags = "教师管理")
@RequestMapping("/teacher")
@RestController
public class TeacherController {

    @Resource
    private TeacherService teacherService;

    @LogRecord(
            fail = "添加教师信息失败，失败原因：「{{#_errorMsg}}」",
            subType = "MANAGER_VIEW",
            success = "{{#detail.userId}}添加教师信息「{{#request.username}}」成功,修改结果:{{#_ret}}",
            operator = "{{#detail.nickName}}", type = LogRecordType.TEACHER, bizNo = "{{#request.username}}")
    @ApiOperation("添加教师")
    @PostMapping("/add/teacher")
    public Result addTeacher(@RequestBody TeacherAddRequest request, StpUserDetail detail) {
        request.setCreateUserId(detail.getUserId());
        request.setCreateUserName(detail.getNickName());
        ValidateUtil.validate(request);
        return ResultGenerator.getResultByOk(teacherService.addTeacher(request));
    }

    @LogRecord(
            fail = "批量删除教师信息失败，失败原因：「{{#_errorMsg}}」",
            subType = "MANAGER_VIEW",
            success = "{{#detail.userId}}批量删除教师信息「{{#teacherIds}}」成功,修改结果:{{#_ret}}",
            operator = "{{#detail.nickName}}", type = LogRecordType.TEACHER, bizNo = "batch_delete_teacher")
    @ApiOperation("批量删除教师")
    @PostMapping("/batch/delete/teacher")
    public Result batchDeleteUser(@RequestBody List<Integer> teacherIds, StpUserDetail detail) {
        if (CollectionUtil.isEmpty(teacherIds)) {
            throw new ApiException("教师ID列表不能为空");
        }
        return teacherService.batchDeleteUser(teacherIds);
    }

    @LogRecord(
            fail = "修改教师信息失败，失败原因：「{{#_errorMsg}}」",
            subType = "MANAGER_VIEW",
            success = "{{#detail.userId}}修改教师信息「{{#request.username}}」成功,修改结果:{{#_ret}}",
            operator = "{{#detail.nickName}}", type = LogRecordType.TEACHER, bizNo = "{{#request.username}}")
    @ApiOperation("修改教师详情")
    @PostMapping("/update/teacher/info")
    public Result updateTeacherInfo(@RequestBody ManageTeacherRequest request, StpUserDetail detail) {
        request.setUpdateUserId(detail.getUserId());
        request.setUpdateUserName(detail.getUsername());
        ValidateUtil.validate(request);
        teacherService.updateTeacherInfo(request);
        return ResultGenerator.getResultByOk("修改成功");
    }

    @ApiOperation("分页获取教师列表")
    @PostMapping("/page/teacher/list")
    public Result<PageTeacherResponse> pageTeacherList(@RequestBody PageTeacherRequest request) {
        ValidateUtil.validate(request);
        return ResultGenerator.getResultByOk(teacherService.pageTeacherList(request));
    }
}
