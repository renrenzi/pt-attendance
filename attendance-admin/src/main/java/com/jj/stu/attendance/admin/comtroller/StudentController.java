package com.jj.stu.attendance.admin.comtroller;

import com.jj.stu.attendance.admin.service.StudentService;
import com.jj.stu.attendance.base.util.ValidateUtil;
import com.jj.stu.attendance.dao.request.student.StudentBatchInsertRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * 学生控制器
 * 学生管理
 *
 * @author 任人子
 * @date 2022/11/4  - {TIME}
 */
@RequestMapping("/student")
@RestController
@Api(tags = "学生管理")
public class StudentController {

    @Resource
    private StudentService studentService;

    /**
     * 批量添加学生
     *
     * @param request 请求
     */
    @PostMapping("/batch/add/student")
    @ApiOperation("批量添加学生")
    public void batchAddStudent(@RequestBody StudentBatchInsertRequest request){
        ValidateUtil.validate(request);
        studentService.batchAddStudent(request);
    }
}
