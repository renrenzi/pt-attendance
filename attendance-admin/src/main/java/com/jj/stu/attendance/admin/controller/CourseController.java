package com.jj.stu.attendance.admin.controller;

import com.jj.stu.attendance.admin.service.CourseService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.basic.StpUserDetail;
import com.jj.stu.attendance.base.constants.LogRecordType;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.admin.util.ValidateUtil;
import com.jj.stu.attendance.meta.request.EditCourseRequest;
import com.jj.stu.attendance.meta.request.PageCourseListRequest;
import com.jj.stu.attendance.meta.response.PageCourseListResponse;
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
 * 当然制控器
 *
 * @author 张俊杰
 * @date 2022/11/19
 */
@Api(tags = "课程管理")
@RequestMapping("/course")
@RestController
public class CourseController {

    @Resource
    private CourseService courseService;

    @LogRecord(
            fail = "创建课程失败，失败原因：「{{#_errorMsg}}」",
            subType = "MANAGER_VIEW",
            success = "{{#detail.userId}}创建课程「{{#request.name}}」,创建课程结果:{{#_ret}}",
            operator = "{{#detail.nickName}}", type = LogRecordType.COURSE, bizNo = "{{#request.id}}")
    @ApiOperation("添加課程")
    @PostMapping("/add/course")
    public Result<String> addCourse(@RequestBody EditCourseRequest request, StpUserDetail detail) {
        ValidateUtil.validate(request);
        courseService.addCourse(request);
        return ResultGenerator.getResultByOk("添加課程成功");
    }

    @LogRecord(
            fail = "修改课程信息失败，失败原因：「{{#_errorMsg}}」",
            subType = "MANAGER_VIEW",
            success = "{{#detail.userId}}修改课程信息「{{#request.name}}」成功,修改结果:{{#_ret}}",
            operator = "{{#detail.nickName}}", type = LogRecordType.COURSE, bizNo = "{{#request.id}}")
    @ApiOperation("修改课程信息")
    @PostMapping("/edit/course/detail")
    public Result<String> editCourseDetail(@RequestBody EditCourseRequest request, StpUserDetail detail) {
        ValidateUtil.validate(request);
        courseService.editCourseDetail(request);
        return ResultGenerator.getResultByOk("成功修改课程信息");
    }

    @LogRecord(
            fail = "批量删除课程信息，失败原因：「{{#_errorMsg}}」",
            subType = "MANAGER_VIEW",
            success = "{{#detail.userId}}批量删除课程信息「{{#courseIds}}」成功,修改结果:{{#_ret}}",
            operator = "{{#detail.nickName}}", type = LogRecordType.COURSE, bizNo = "{{#courseIds}}")
    @ApiOperation("批量删除课程信息")
    @PostMapping("/batch/delete/course")
    public Result<String> batchDeleteCourseByIds(@RequestBody List<Integer> courseIds, StpUserDetail detail) {
        if (CollectionUtils.isEmpty(courseIds)) {
            throw new ApiException("courseIds 不能为空");
        }
        courseService.batchDeleteCourseByIds(courseIds);
        return ResultGenerator.getResultByOk("成功删除课程信息");
    }

    @ApiOperation("分页获取课程列表")
    @PostMapping("/page/course/list")
    public Result<PageCourseListResponse> pageCourseList(@RequestBody PageCourseListRequest request) {
        ValidateUtil.validate(request);
        return ResultGenerator.getResultByOk(courseService.pageCourseList(request));
    }


}
