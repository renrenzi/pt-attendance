package com.jj.stu.attendance.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jj.stu.attendance.admin.service.ClazzService;
import com.jj.stu.attendance.dao.mapper.ClazzMapper;
import com.jj.stu.attendance.dao.model.Clazz;
import com.jj.stu.attendance.dao.request.clazz.PageClazzRequest;
import com.jj.stu.attendance.dao.response.clazz.PageClazzResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * impl clazz服务
 *
 * @author 张俊杰
 * @date 2022/11/19
 */
@Service
public class ClazzServiceImpl extends ServiceImpl<ClazzMapper, Clazz> implements ClazzService {

    @Resource
    private ClazzMapper clazzMapper;

    @Override
    public PageClazzResponse pageClazzList(PageClazzRequest request) {
        QueryWrapper<Clazz> wrapper = new QueryWrapper<>();
        Page<Object> page = PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Clazz> clazzList = clazzMapper.selectList(wrapper);
        return new PageClazzResponse()
                .setClazzList(clazzList)
                .setTotalSize(page.getTotal());
    }
}
