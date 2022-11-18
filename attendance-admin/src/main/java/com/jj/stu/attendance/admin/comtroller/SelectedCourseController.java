package com.jj.stu.attendance.admin.comtroller;

import com.jj.stu.attendance.admin.service.SelectedCourseService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.util.ValidateUtil;
import com.jj.stu.attendance.dao.request.selectedCourse.PageSelectedCourseRequest;
import com.jj.stu.attendance.dao.response.selectedCourse.PageSelectedCourseResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "选课管理")
@RequestMapping("/selectedCourse")
@RestController
public class SelectedCourseController {
    @Resource
    private SelectedCourseService selectedCourseService;

    @ApiOperation("分页获取选课列表")
    @PostMapping("/page/slected/course/list")
    public Result<PageSelectedCourseResponse> pageSelectedCourseList(@RequestBody PageSelectedCourseRequest request){
        ValidateUtil.validate(request);
        return ResultGenerator.getResultByOk(selectedCourseService.pageSelectedCourseList(request));
    }
}
