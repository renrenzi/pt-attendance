package com.jj.stu.attendance.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jj.stu.attendance.admin.basic.PageCondition;
import com.jj.stu.attendance.admin.basic.PageResult;
import com.jj.stu.attendance.dao.model.UserResourceCategory;

/**
 * 用户资源分类管理Service
 *
 * @author 任人子
 * @date 2022/5/10  - {TIME}
 */
public interface UserResourceCategoryService extends IService<UserResourceCategory> {
    /**
     * 页面资源类别
     *
     * @param condition            条件
     * @param userResourceCategory 用户资源类别
     * @return {@link Object}
     */
    PageResult<UserResourceCategory> pageResourceCategory(PageCondition condition, UserResourceCategory userResourceCategory);
}
