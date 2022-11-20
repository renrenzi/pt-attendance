package com.jj.stu.attendance.admin.controller;


import com.jj.stu.attendance.admin.service.TeacherService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.util.ValidateUtil;
import com.jj.stu.attendance.dao.request.teacher.PageTeacherRequest;
import com.jj.stu.attendance.dao.response.teacher.PageTeacherResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

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

    @ApiOperation("分页获取教师列表")
    @PostMapping("/page/teacher/list")
    public Result<PageTeacherResponse> pageTeacherList(@RequestBody PageTeacherRequest request){
        ValidateUtil.validate(request);
        return ResultGenerator.getResultByOk(teacherService.pageTeacherList(request));
    }
}
