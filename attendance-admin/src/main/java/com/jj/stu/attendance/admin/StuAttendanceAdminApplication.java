package com.jj.stu.attendance.admin;

import com.mzt.logapi.starter.annotation.EnableLogRecord;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * admin 主启动类
 *
 * @author 任人子
 * @date 2022/11/2  - {TIME}
 */

@SpringBootApplication(scanBasePackages = {"com.jj.stu.attendance.admin.*", "com.jj.stu.attendance.base",
        "com.jj.stu.attendance.dao", "com.jj.stu.attendance.meta", "com.jj.stu.attendance.task"})
@EnableLogRecord(tenant = "com.jj.stu.attendance.admin")
public class StuAttendanceAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(StuAttendanceAdminApplication.class, args);
    }

}
