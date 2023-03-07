package com.jj.stu.attendance.admin.basic;

import com.jj.stu.attendance.admin.service.UserResourceService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

@Component
public class RoleResourceConstructor {

    @Resource
    private UserResourceService userResourceService;

    @PostConstruct
    public void initRoleResourceMap() {
        userResourceService.initRoleResourceMap();
    }
}
