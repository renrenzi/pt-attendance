package com.jj.stu.attendance.admin.controller;

import com.jj.stu.attendance.admin.service.AttendanceService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.util.ValidateUtil;
import com.jj.stu.attendance.dao.request.attendance.PageAttendanceRequest;
import com.jj.stu.attendance.dao.response.attendance.PageAttendanceResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 考勤控制器
 *
 * @author 张俊杰
 * @date 2022/11/19
 */
@Api(tags = "考勤管理")
@RequestMapping("/attendance")
@RestController
public class AttendanceController {

    @Resource
    private AttendanceService attendanceService;

    @ApiOperation("分页获取考勤列表")
    @PostMapping("/page/attendance/list")
    public Result<PageAttendanceResponse> pageAttendanceList(@RequestBody PageAttendanceRequest request){
        ValidateUtil.validate(request);
        return ResultGenerator.getResultByOk(attendanceService.pageAttendanceList(request));
    }
}
