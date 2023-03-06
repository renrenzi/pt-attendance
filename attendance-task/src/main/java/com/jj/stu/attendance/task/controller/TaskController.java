package com.jj.stu.attendance.task.controller;

import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api("任务管理")
@RequestMapping("/task")
@RestController
public class TaskController {

    @PostMapping("/add/task")
    public Result addTask() {
        System.out.println("task add ");
        return ResultGenerator.getResultByOk(null);
    }
}
