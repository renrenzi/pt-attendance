package com.jj.stu.attendance.admin.controller;

import com.jj.stu.attendance.admin.service.SelectedCourseService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.basic.StpUserDetail;
import com.jj.stu.attendance.base.constants.LogRecordType;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.admin.util.ValidateUtil;
import com.jj.stu.attendance.meta.request.ManageSelectedCourseRequest;
import com.jj.stu.attendance.meta.request.PageSelectedCourseRequest;
import com.jj.stu.attendance.meta.response.PageSelectedCourseResponse;
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
 * 选修课控制器
 *
 * @author zhangjunjie
 * @date 2022/11/19
 */
@Api(tags = "选课管理")
@RequestMapping("/selectedCourse")
@RestController
public class SelectedCourseController {
    @Resource
    private SelectedCourseService selectedCourseService;

    @LogRecord(
            fail = "修改选课内容失败，失败原因：「{{#_errorMsg}}」",
            subType = "MANAGER_VIEW",
            success = "{{#detail.userId}}修改选课内容「{{#request.studentId}}」,修改选课内容结果:{{#_ret}}",
            operator = "{{#detail.nickName}}",  type = LogRecordType.SELECTED_COURSE, bizNo = "{{#request.id}}")
    @ApiOperation("修改选课内容")
    @PostMapping("/update/selected/course")
    public Result<String> updateSelectedCourse(@RequestBody ManageSelectedCourseRequest request, StpUserDetail detail){
        ValidateUtil.validate(request);
        selectedCourseService.updateSelectedCourse(request);
        return ResultGenerator.getResultByOk("修改选课内容成功");
    }

    @LogRecord(
            fail = "批量刪除选课列表失败，失败原因：「{{#_errorMsg}}」",
            subType = "MANAGER_VIEW",
            success = "{{#detail.userId}}批量刪除选课列表「{{#selectedCourseIds}}」成功,修改结果:{{#_ret}}",
            operator = "{{#detail.nickName}}", type = LogRecordType.SELECTED_COURSE, bizNo = "{{#selectedCourseIds}}")
    @ApiOperation("批量刪除選課列表")
    @PostMapping("/batch/delete/selected/course/list")
    public Result<String> batchDeleteSelectedCourseList(@RequestBody List<Integer> selectedCourseIds, StpUserDetail detail){
        if(CollectionUtils.isEmpty(selectedCourseIds)){
            throw new ApiException("選課列表爲空");
        }
        selectedCourseService.batchDeleteSelectedCourseList(selectedCourseIds);
        return  ResultGenerator.getResultByOk("批量刪除選課列表成功");
    }

    @ApiOperation("分页获取选课列表")
    @PostMapping("/page/selected/course/list")
    public Result<PageSelectedCourseResponse> pageSelectedCourseList(@RequestBody PageSelectedCourseRequest request){
        ValidateUtil.validate(request);
        return ResultGenerator.getResultByOk(selectedCourseService.pageSelectedCourseList(request));
    }
}
