package com.jj.stu.attendance.admin.controller;

import com.jj.stu.attendance.admin.service.AttendanceService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.base.util.ValidateUtil;
import com.jj.stu.attendance.meta.request.ManageAttendanceRequest;
import com.jj.stu.attendance.meta.request.PageAttendanceRequest;
import com.jj.stu.attendance.meta.request.PunchTheClockRequest;
import com.jj.stu.attendance.meta.response.PageAttendanceResponse;
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

    @ApiOperation("打卡")
    @PostMapping("/punch/the/clock")
    public Result punchTheClock(@RequestBody PunchTheClockRequest request){
        ValidateUtil.validate(request);
        return attendanceService.punchTheClock(request);
    }

    @ApiOperation("修改考勤信息")
    @PostMapping("/update/attendance/info")
    public Result<String> updateAttendanceInfo(@RequestBody ManageAttendanceRequest request){
        ValidateUtil.validate(request);
        attendanceService.updateAttendanceInfo(request);
        return ResultGenerator.getResultByOk("修改成功");
    }
    @ApiOperation("批量删除考勤列表")
    @PostMapping("/batch/delete/attendance/list")
    public Result<String> batchDeleteAttendanceList(@RequestBody List<Integer> attendanceIds){
        if (CollectionUtils.isEmpty(attendanceIds)){
            throw new ApiException("考勤id列表不能为空");
        }
        attendanceService.batchDeleteAttendanceList(attendanceIds);
        return ResultGenerator.getResultByOk("删除成功");
    }
    @ApiOperation("分页获取考勤列表")
    @PostMapping("/page/attendance/list")
    public Result<PageAttendanceResponse> pageAttendanceList(@RequestBody PageAttendanceRequest request){
        ValidateUtil.validate(request);
        return ResultGenerator.getResultByOk(attendanceService.pageAttendanceList(request));
    }
}
