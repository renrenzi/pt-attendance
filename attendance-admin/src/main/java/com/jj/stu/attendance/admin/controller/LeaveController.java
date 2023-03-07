package com.jj.stu.attendance.admin.controller;

import com.jj.stu.attendance.admin.service.LeaveService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.basic.StpUserDetail;
import com.jj.stu.attendance.base.constants.LogRecordType;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.admin.util.ValidateUtil;
import com.jj.stu.attendance.meta.request.ManageLeaveRequest;
import com.jj.stu.attendance.meta.request.PageLeaveRequest;
import com.jj.stu.attendance.meta.response.PageLeaveResponse;
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
 * 请假控制器
 *
 * @author zhangjunjie
 * @date 2023/01/12
 */
@Api(tags = "请假管理")
@RequestMapping("/leave")
@RestController
public class LeaveController {

    @Resource
    private LeaveService leaveService;

    @LogRecord(
            fail = "修改请假信息失败，失败原因：「{{#_errorMsg}}」",
            subType = "MANAGER_VIEW",
            success = "{{#detail.userId}}修改请假信息「{{#request.studentId}}」,修改请假信息结果:{{#_ret}}",
            operator = "{{#detail.nickName}}", type = LogRecordType.LEAVE, bizNo = "{{#request.id}}")
    @ApiOperation("修改请假信息")
    @PostMapping("/update/leave/info")
    public Result<String> updateLeaveInfo(@RequestBody ManageLeaveRequest request, StpUserDetail detail) {
        ValidateUtil.validate(request);
        leaveService.updateLeaveInfo(request);
        return ResultGenerator.getResultByOk("修改成功");
    }

    @LogRecord(
            fail = "批量删除请假列表失败，失败原因：「{{#_errorMsg}}」",
            subType = "MANAGER_VIEW",
            success = "{{#detail.userId}}批量删除请假列表「{{#leaveIds}}」成功,修改结果:{{#_ret}}",
            operator = "{{#detail.nickName}}", type = LogRecordType.LEAVE, bizNo = "{{#leaveIds}}")
    @ApiOperation("批量删除请假列表")
    @PostMapping("/batch/delete/leave/list")
    public Result<String> batchDeleteLeaveList(@RequestBody List<Integer> leaveIds, StpUserDetail detail) {
        if (CollectionUtils.isEmpty(leaveIds)) {
            throw new ApiException("leaveIds 为空");
        }
        ValidateUtil.validate(leaveIds);
        leaveService.batchDeleteLeaveList(leaveIds);
        return ResultGenerator.getResultByOk("删除成功");
    }

    @ApiOperation("分页获取请假列表")
    @PostMapping("/page/leave/list")
    public Result<PageLeaveResponse> pageLeaveList(@RequestBody PageLeaveRequest request) {
        ValidateUtil.validate(request);
        return ResultGenerator.getResultByOk(leaveService.pageLeaveList(request));
    }
}
