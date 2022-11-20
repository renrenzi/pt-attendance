package com.jj.stu.attendance.admin.controller;

import com.jj.stu.attendance.admin.service.LeaveService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.util.ValidateUtil;
import com.jj.stu.attendance.dao.request.leave.PageLeaveRequest;
import com.jj.stu.attendance.dao.response.leave.PageLeaveResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 离开控制器
 *
 * @author 张俊杰
 * @date 2022/11/20
 */
@Api(tags = "请假管理")
@RequestMapping("/leave")
@RestController
public class LeaveController {

    @Resource
    private LeaveService leaveService;

    @ApiOperation("分页获取请假列表")
    @PostMapping("/page/leave/list")
    public Result<PageLeaveResponse> pageLeaveList(@RequestBody PageLeaveRequest request){
        ValidateUtil.validate(request);
        return ResultGenerator.getResultByOk(leaveService.pageLeaveList(request));
    }
}
