package com.jj.stu.attendance.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jj.stu.attendance.admin.basic.PageCondition;
import com.jj.stu.attendance.admin.basic.PageResult;
import com.jj.stu.attendance.admin.service.UserResourceCategoryService;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.constants.HttpStatusEnum;
import com.jj.stu.attendance.dao.mapper.UserResourceCategoryMapper;
import com.jj.stu.attendance.dao.model.UserResourceCategory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户资源分类管理ServiceImpl
 * @author 任人子
 * @date 2022/5/10  - {TIME}
 */
@Service
public class UserResourceCategoryServiceImpl extends ServiceImpl<UserResourceCategoryMapper, UserResourceCategory> implements UserResourceCategoryService {
    @Resource
    private UserResourceCategoryMapper userResourceCategoryMapper;
    @Override
    public PageResult<UserResourceCategory> pageResourceCategory(PageCondition condition, UserResourceCategory userResourceCategory) {
        QueryWrapper<UserResourceCategory> query = new QueryWrapper<>(userResourceCategory);
        query.lambda().orderByDesc(UserResourceCategory::getCreateTime);
        Page<Object> page = PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        List<UserResourceCategory> userResourceCategoryList = userResourceCategoryMapper.selectList(query);
        PageResult<UserResourceCategory> pageResult = new PageResult<>();
        pageResult.setTotalSize(page.getTotal());
        pageResult.setData(userResourceCategoryList);
        return pageResult;
    }
}
