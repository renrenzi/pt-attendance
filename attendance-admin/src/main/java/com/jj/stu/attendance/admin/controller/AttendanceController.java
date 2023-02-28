package com.jj.stu.attendance.admin.controller;

import com.jj.stu.attendance.admin.service.AttendanceService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.basic.StpUserDetail;
import com.jj.stu.attendance.base.constants.LogRecordType;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.admin.util.ValidateUtil;
import com.jj.stu.attendance.meta.request.ManageAttendanceRequest;
import com.jj.stu.attendance.meta.request.PageAttendanceRequest;
import com.jj.stu.attendance.meta.request.PunchTheClockRequest;
import com.jj.stu.attendance.meta.response.PageAttendanceResponse;
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

    @LogRecord(
            fail = "修改考勤信息失败，失败原因：「{{#_errorMsg}}」",
            subType = "MANAGER_VIEW",
            success = "{{#request.studentId}}修改考勤信息「{{#request.studentId}}」,修改结果:{{#_ret}}",
            operator = "{{#detail.nickName}}", type = LogRecordType.ATTENDANCE, bizNo = "{{#request.id}}")
    @ApiOperation("修改考勤信息")
    @PostMapping("/update/attendance/info")
    public Result<String> updateAttendanceInfo(@RequestBody ManageAttendanceRequest request, StpUserDetail detail){
        ValidateUtil.validate(request);
        request.setUserId(detail.getUserId());
        attendanceService.updateAttendanceInfo(request);
        return ResultGenerator.getResultByOk("修改成功");
    }

    @LogRecord(
            fail = "批量删除考勤信息失败，失败原因：「{{#_errorMsg}}」",
            subType = "MANAGER_VIEW",
            success = "{{#detail.userId}}批量删除考勤信息「{{#attendanceIds}}」,批量删除结果:{{#_ret}}",
            operator = "{{#detail.nickName}}", type = LogRecordType.ATTENDANCE, bizNo = "{{#attendanceIds}}")
    @ApiOperation("批量删除考勤列表")
    @PostMapping("/batch/delete/attendance/list")
    public Result<String> batchDeleteAttendanceList(@RequestBody List<Integer> attendanceIds, StpUserDetail detail){
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
