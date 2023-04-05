package com.jj.stu.attendance.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jj.stu.attendance.admin.basic.PageCondition;
import com.jj.stu.attendance.admin.basic.PageResult;
import com.jj.stu.attendance.admin.constants.CommonStatusEnum;
import com.jj.stu.attendance.admin.service.UserRoleService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.dao.mapper.UserRoleMapper;
import com.jj.stu.attendance.dao.model.UserRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户角色ServiceImpl
 *
 * @author 任人子
 * @date 2022/5/9  - {TIME}
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    @Resource
    private UserRoleMapper userRoleMapper;

    @Override
    public PageResult<UserRole> pageRole(PageCondition condition, UserRole userRole) {
        QueryWrapper<UserRole> query = new QueryWrapper<>(userRole);
        query.lambda().orderByDesc(UserRole::getCreateTime);
        Page<Object> page = PageHelper.startPage(condition.getPageNum(), condition.getPageSize());
        List<UserRole> userRoleList = userRoleMapper.selectList(query);
        PageResult<UserRole> pageResult = new PageResult<>();
        pageResult.setTotalSize(page.getTotal());
        pageResult.setData(userRoleList);
        return pageResult;
    }

    @Override
    public Result<List<UserRole>> getAllEnableRole() {
        return ResultGenerator.getResultByOk(userRoleMapper.selectList(new QueryWrapper<UserRole>()
                .lambda().eq(UserRole::getRoleStatus, CommonStatusEnum.ENABLE.getCode())));
    }

}
