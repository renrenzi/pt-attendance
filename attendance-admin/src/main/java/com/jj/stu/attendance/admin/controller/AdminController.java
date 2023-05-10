package com.jj.stu.attendance.admin.controller;

import com.jj.stu.attendance.admin.basic.PageResult;
import com.jj.stu.attendance.admin.service.AdminService;
import com.jj.stu.attendance.admin.util.ValidateUtil;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.basic.StpUserDetail;
import com.jj.stu.attendance.base.constants.LogRecordType;
import com.jj.stu.attendance.meta.request.EditAdminInfoRequest;
import com.jj.stu.attendance.meta.request.MiniLoginRequest;
import com.jj.stu.attendance.meta.request.PageAdminListRequest;
import com.jj.stu.attendance.meta.request.UserLoginRequest;
import com.jj.stu.attendance.meta.response.PageAdminInfoResponse;
import com.mzt.logapi.starter.annotation.LogRecord;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 管理员控制器
 *
 * @author renrenzi
 * @date 2023/01/12
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @ApiOperation("后台用户登录")
    @PostMapping("/user/login/info")
    public Result userLoginInfo(@RequestBody UserLoginRequest request) {
        ValidateUtil.validate(request);
        return adminService.userLoginInfo(request);
    }

    @ApiOperation("前台用户登录")
    @PostMapping("/mini/login/info")
    public Result miniLoginInfo(@RequestBody MiniLoginRequest request) {
        ValidateUtil.validate(request);
        return adminService.miniLoginInfo(request);
    }

    @ApiOperation("分页查询用户列表")
    @PostMapping("/page/admin/info/list")
    public Result<PageResult<PageAdminInfoResponse>> pageAdminInfoList(@RequestBody PageAdminListRequest request) {
        ValidateUtil.validate(request);
        return adminService.pageAdminInfoList(request);
    }

    @LogRecord(
            fail = "修改账号信息失败，失败原因：「{{#_errorMsg}}」",
            subType = "MANAGER_VIEW",
            success = "{{#detail.userId}}修改账号信息「{{#request.adminId}}」,修改结果:{{#_ret}}",
            operator = "{{#detail.nickName}}", type = LogRecordType.ADMIN_USER, bizNo = "{{#request.adminId}}")

    @ApiOperation("修改账号信息")
    @PostMapping("/edit/admin/info")
    public Result<String> editAdminInfo(@RequestBody EditAdminInfoRequest request, StpUserDetail detail) {
        request.setUpdateUserId(detail.getUserId());
        request.setUpdateUserName(detail.getNickName());
        ValidateUtil.validate(request);
        return ResultGenerator.getResultByOk(adminService.editAdminInfo(request));
    }

}
