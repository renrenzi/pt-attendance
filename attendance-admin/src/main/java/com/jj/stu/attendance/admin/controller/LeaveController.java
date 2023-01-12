package com.jj.stu.attendance.admin.controller;

import com.jj.stu.attendance.admin.service.LeaveService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.base.util.ValidateUtil;
import com.jj.stu.attendance.dao.request.leave.ManageLeaveRequest;
import com.jj.stu.attendance.dao.request.leave.PageLeaveRequest;
import com.jj.stu.attendance.dao.response.leave.PageLeaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;


/**
 * 请假控制器
 *
 * @author LENOVO
 * @date 2023/01/12
 */
@Api(tags = "请假管理")
@RequestMapping("/leave")
@RestController
public class LeaveController {

    @Resource
    private LeaveService leaveService;

    @ApiOperation("修改请假信息")
    @PostMapping("/update/leave/info")
    public Result<String> updateLeaveInfo(@RequestBody ManageLeaveRequest request){
        ValidateUtil.validate(request);
        leaveService.updateLeaveInfo(request);
        return ResultGenerator.getResultByOk("修改成功");
    }
    @ApiOperation("批量删除请假列表")
    @PostMapping("/batch/delete/leave/list")
    public Result<String> batchDeleteLeaveList(@RequestBody List<Integer> leaveIds){
        if(CollectionUtils.isEmpty(leaveIds)){
            throw new ApiException("leaveIds 为空");
        }
        ValidateUtil.validate(leaveIds);
        leaveService.batchDeleteLeaveList(leaveIds);
        return ResultGenerator.getResultByOk("删除成功");
    }
    @ApiOperation("分页获取请假列表")
    @PostMapping("/page/leave/list")
    public Result<PageLeaveResponse> pageLeaveList(@RequestBody PageLeaveRequest request){
        ValidateUtil.validate(request);
        return ResultGenerator.getResultByOk(leaveService.pageLeaveList(request));
    }
}
