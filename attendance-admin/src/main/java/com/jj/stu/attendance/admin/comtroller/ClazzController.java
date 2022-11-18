package com.jj.stu.attendance.admin.comtroller;


import com.jj.stu.attendance.admin.service.ClazzService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.util.ValidateUtil;
import com.jj.stu.attendance.dao.request.clazz.PageClazzRequest;
import com.jj.stu.attendance.dao.response.clazz.PageClazzResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Api(tags = "专业管理")
@RequestMapping("/clazz")
public class ClazzController {
    @Resource
    private ClazzService clazzService;

    @ApiOperation("分页获取专业列表")
    @PostMapping("/page/clazz/list")
    public Result<PageClazzResponse> pageClazzList(@RequestBody PageClazzRequest request){
        ValidateUtil.validate(request);
        return ResultGenerator.getResultByOk(clazzService.pageClazzList(request));
    }

}
