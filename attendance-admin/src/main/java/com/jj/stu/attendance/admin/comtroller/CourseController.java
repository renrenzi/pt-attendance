package com.jj.stu.attendance.admin.comtroller;

import com.jj.stu.attendance.admin.service.CourseService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.dao.request.course.PageCourseListRequest;
import com.jj.stu.attendance.dao.response.course.PageCourseListResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 */
@Api(tags = "课程管理")
@RequestMapping("/course")
@RestController
public class CourseController {

    @Resource
    private CourseService courseService;

    @ApiOperation("分页获取课程列表")
    @PostMapping("/page/course/list")
    public Result<PageCourseListResponse> pageCourseList(@RequestBody PageCourseListRequest request){
        return ResultGenerator.getResultByOk(courseService.pageCourseList(request));
    }
}
