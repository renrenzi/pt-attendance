package com.jj.stu.attendance.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jj.stu.attendance.admin.service.TeacherService;
import com.jj.stu.attendance.dao.mapper.TeacherMapper;
import com.jj.stu.attendance.dao.model.Teacher;
import com.jj.stu.attendance.dao.request.teacher.PageTeacherRequest;
import com.jj.stu.attendance.dao.response.teacher.PageTeacherResponse;
import jdk.nashorn.internal.ir.annotations.Reference;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 教师服务impl
 *
 * @author 张俊杰
 * @date 2022/11/19
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Resource
    private TeacherMapper  teacherMapper;

    @Override
    public PageTeacherResponse pageTeacherList(PageTeacherRequest request) {
        Page<Object> page = PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Teacher> teacherList = teacherMapper.selectList(new QueryWrapper<>());
        return new PageTeacherResponse()
                .setTeacherList(teacherList)
                .setTotalSize(page.getTotal());
    }
}
