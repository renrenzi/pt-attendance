package com.jj.stu.attendance.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jj.stu.attendance.admin.basic.PageCondition;
import com.jj.stu.attendance.admin.basic.PageResult;
import com.jj.stu.attendance.dao.model.UserResource;

import java.util.List;
import java.util.Map;

/**
 * 用户资源Service
 *
 * @author 任人子
 * @date 2022/5/10  - {TIME}
 */
public interface UserResourceService extends IService<UserResource> {

    /**
     * 初始化资源角色关系Map并保存到Redis中
     *
     * @return
     */
    Map<String, List<String>> initRoleResourceMap();

    /**
     * 页面资源
     *
     * @param condition    条件
     * @param userResource 用户资源
     * @return {@link Object}
     */
    PageResult<UserResource> pageResource(PageCondition condition, UserResource userResource);
}
