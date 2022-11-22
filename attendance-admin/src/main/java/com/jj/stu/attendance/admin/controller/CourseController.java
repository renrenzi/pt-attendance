package com.jj.stu.attendance.admin.controller;

import com.jj.stu.attendance.admin.service.CourseService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.base.util.ValidateUtil;
import com.jj.stu.attendance.dao.model.Course;
import com.jj.stu.attendance.dao.request.course.EditCourseRequest;
import com.jj.stu.attendance.dao.request.course.PageCourseListRequest;
import com.jj.stu.attendance.dao.response.course.PageCourseListResponse;
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

    @ApiOperation("修改课程信息")
    @PostMapping("/edit/course/detail")
    public Result<String> editCourseDetail(@RequestBody EditCourseRequest request){
        ValidateUtil.validate(request);
        courseService.editCourseDetail(request);
        return ResultGenerator.getResultByOk("成功修改课程信息");
    }

    @ApiOperation("批量删除课程信息")
    @PostMapping("/batch/delete/course")
    public Result<String> batchDeleteCourseByIds(@RequestBody List<Integer> courseIds){
        if(CollectionUtils.isEmpty(courseIds)){
            throw new ApiException("courseIds 不能为空");
        }
        courseService.batchDeleteCourseByIds(courseIds);
        return ResultGenerator.getResultByOk("成功删除课程信息");
    }
    /**
     * 课程列表页
     *
     * @param request 请求
     * @return {@link Result}<{@link PageCourseListResponse}>
     */
    @ApiOperation("分页获取课程列表")
    @PostMapping("/page/course/list")
    public Result<PageCourseListResponse> pageCourseList(@RequestBody PageCourseListRequest request){
        ValidateUtil.validate(request);
        return ResultGenerator.getResultByOk(courseService.pageCourseList(request));
    }


}
