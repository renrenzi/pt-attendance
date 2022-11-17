package com.jj.stu.attendance.admin.comtroller;

import cn.hutool.core.collection.CollectionUtil;
import com.jj.stu.attendance.admin.service.StudentService;
import com.jj.stu.attendance.dao.request.StudentBatchInsertRequest;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.Collection;

/**
 * 学生管理
 * @author 任人子
 * @date 2022/11/4  - {TIME}
 */
@RequestMapping("/student")
@RestController
@Api(tags = "StudentController")
public class StudentController {

    @Resource
    private StudentService studentService;

    @PostMapping("/batch/add/student")
    @ApiOperation("批量添加学生")
    public void batchAddStudent(StudentBatchInsertRequest request){
        if(CollectionUtils.isEmpty(request.getStudentList())){
            return;
        }
        studentService.batchAddStudent(request);
    }
}
