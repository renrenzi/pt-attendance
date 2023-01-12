package com.jj.stu.attendance.admin.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.jj.stu.attendance.admin.service.AdminService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.util.ValidateUtil;
import com.jj.stu.attendance.dao.request.MiniLoginRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 *
 * @author renrenzi
 */
@Api(tags = "用户管理")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Resource
    private AdminService adminService;

    @PostMapping("/mini/login/info")
    @ApiOperation("前台用户登录")
    public Result miniLoginInfo(@RequestBody MiniLoginRequest request){
        ValidateUtil.validate(request);
        return adminService.miniLoginInfo(request);
    }
}
