package com.jj.stu.attendance.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jj.stu.attendance.admin.service.UserResourceCategoryService;
import com.jj.stu.attendance.dao.mapper.UserResourceCategoryMapper;
import com.jj.stu.attendance.dao.model.UserResourceCategory;
import org.springframework.stereotype.Service;

/**
 * 用户资源分类管理ServiceImpl
 * @author 任人子
 * @date 2022/5/10  - {TIME}
 */
@Service
public class UserResourceCategoryServiceImpl extends ServiceImpl<UserResourceCategoryMapper, UserResourceCategory> implements UserResourceCategoryService {
}
