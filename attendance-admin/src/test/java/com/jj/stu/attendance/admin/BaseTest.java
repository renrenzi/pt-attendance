package com.jj.stu.attendance.admin;

import lombok.extern.slf4j.Slf4j;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

@Rollback
@RunWith(SpringRunner.class)
@SpringBootTest(classes = StuAttendanceAdminApplication.class)
@Slf4j
public class BaseTest {
}
