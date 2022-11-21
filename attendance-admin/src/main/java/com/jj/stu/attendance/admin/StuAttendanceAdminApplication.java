package com.jj.stu.attendance.admin;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * admin 主启动类
 *
 * @author 任人子
 * @date 2022/11/2  - {TIME}
 */

@SpringBootApplication(scanBasePackages = {"com.jj.stu.attendance"})
public class StuAttendanceAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(StuAttendanceAdminApplication.class, args);
    }

}
