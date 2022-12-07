package com.jj.stu.attendance.admin.controller;

import com.jj.stu.attendance.admin.service.SelectedCourseService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.base.util.ValidateUtil;
import com.jj.stu.attendance.dao.request.selectedCourse.ManageSelectedCourseRequest;
import com.jj.stu.attendance.dao.request.selectedCourse.PageSelectedCourseRequest;
import com.jj.stu.attendance.dao.response.selectedCourse.PageSelectedCourseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * 选修课控制器
 *
 * @author 张俊杰
 * @date 2022/11/19
 */
@Api(tags = "选课管理")
@RequestMapping("/selectedCourse")
@RestController
public class SelectedCourseController {
    @Resource
    private SelectedCourseService selectedCourseService;
    @ApiOperation("修改选课内容")
    @PostMapping("/update/selected/course")
    public Result<String> updateSelectedCourse(@RequestBody ManageSelectedCourseRequest request){
        ValidateUtil.validate(request);
        selectedCourseService.updateSelectedCourse(request);
        return ResultGenerator.getResultByOk("修改选课内容成功");
    }
    @ApiOperation("批量刪除選課列表")
    @PostMapping("/batch/delete/selected/course/list")
    public Result<String> batchDeleteSelectedCourseList(@RequestBody List<Integer> selectedCourseIds){
        if(CollectionUtils.isEmpty(selectedCourseIds)){
            throw new ApiException("選課列表爲空");
        }
        selectedCourseService.batchDeleteSelectedCourseList(selectedCourseIds);
        return  ResultGenerator.getResultByOk("批量刪除選課列表成功");
    }
    /**
     * 页面选择课程列表
     *
     * @param request 请求
     * @return {@link Result}<{@link PageSelectedCourseResponse}>
     */
    @ApiOperation("分页获取选课列表")
    @PostMapping("/page/selected/course/list")
    public Result<PageSelectedCourseResponse> pageSelectedCourseList(@RequestBody PageSelectedCourseRequest request){
        ValidateUtil.validate(request);
        return ResultGenerator.getResultByOk(selectedCourseService.pageSelectedCourseList(request));
    }
}
