package com.jj.stu.attendance.admin.controller;

import com.jj.stu.attendance.admin.basic.PageResult;
import com.jj.stu.attendance.admin.service.AdminService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.util.ValidateUtil;
import com.jj.stu.attendance.meta.request.MiniLoginRequest;
import com.jj.stu.attendance.meta.request.PageAdminListRequest;
import com.jj.stu.attendance.meta.response.PageAdminInfoResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

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
    @ApiOperation("前台用户登录")
    @PostMapping("/mini/login/info")
    public Result miniLoginInfo(@RequestBody MiniLoginRequest request){
        ValidateUtil.validate(request);
        return adminService.miniLoginInfo(request);
    }
    @ApiOperation("分页查询用户列表")
    @PostMapping("/page/admin/info/list")
    public Result<PageResult> pageAdminInfoList(@RequestBody PageAdminListRequest request){
        ValidateUtil.validate(request);
        return adminService.pageAdminInfoList(request);
    }
}
