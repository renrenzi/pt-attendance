package com.jj.stu.attendance.admin.controller;


import com.jj.stu.attendance.admin.service.ClazzService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.basic.StpUserDetail;
import com.jj.stu.attendance.base.constants.LogRecordType;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.base.util.ValidateUtil;
import com.jj.stu.attendance.meta.request.ManageClazzRequest;
import com.jj.stu.attendance.meta.request.PageClazzRequest;
import com.jj.stu.attendance.meta.response.PageClazzResponse;
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
 * clazz控制器
 *
 * @author 张俊杰
 * @date 2022/11/19
 */
@RestController
@Api(tags = "专业管理")
@RequestMapping("/clazz")
public class ClazzController {
    @Resource
    private ClazzService clazzService;

    @LogRecord(
            fail = "修改专业详情，失败原因：「{{#_errorMsg}}」",
            subType = "MANAGER_VIEW",
            success = "{{#detail.userId}}修改专业详情「{{#request.name}}」成功,修改结果:{{#_ret}}",
            operator = "{{#detail.nickName}}", type = LogRecordType.CLAZZ, bizNo = "{{#request.id}}")
    @ApiOperation("修改专业详情")
    @PostMapping("/edit/clazz/detail")
    public Result<String> editClazzDetail(@RequestBody ManageClazzRequest request, StpUserDetail detail){
        ValidateUtil.validate(request);
        clazzService.editClazzDetail(request);
        return  ResultGenerator.getResultByOk("修改专业详情");
    }

    @LogRecord(
            fail = "批量删除考勤信息失败，失败原因：「{{#_errorMsg}}」",
            subType = "MANAGER_VIEW",
            success = "{{#detail.userId}}批量删除专业列表「{{#clazzIds}}」,批量删除结果:{{#_ret}}",
            operator = "{{#detail.nickName}}", type = LogRecordType.CLAZZ, bizNo = "{{#clazzIds}}")
    @ApiOperation("批量刪除专业列表")
    @PostMapping("/batch/delete/clazz/list")
    public Result<String> batchDeleteClazzList(@RequestBody List<Integer> clazzIds, StpUserDetail detail){
        if(CollectionUtils.isEmpty(clazzIds)){
            throw new ApiException("clazzIds 不能为空");
        }
        clazzService.batchDeleteClazzList(clazzIds);
        return ResultGenerator.getResultByOk("批量删除专业列表成功");
    }

    @ApiOperation("分页获取专业列表")
    @PostMapping("/page/clazz/list")
    public Result<PageClazzResponse> pageClazzList(@RequestBody PageClazzRequest request){
        ValidateUtil.validate(request);
        return ResultGenerator.getResultByOk(clazzService.pageClazzList(request));
    }

}
