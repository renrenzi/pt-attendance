package com.jj.stu.attendance.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jj.stu.attendance.admin.service.SelectedCourseService;
import com.jj.stu.attendance.dao.mapper.SelectedCourseMapper;
import com.jj.stu.attendance.dao.model.SelectedCourse;
import com.jj.stu.attendance.dao.request.selectedCourse.PageSelectedCourseRequest;
import com.jj.stu.attendance.dao.response.selectedCourse.PageSelectedCourseResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 所选课程服务impl
 *
 * @author 张俊杰
 * @date 2022/11/19
 */
@Service
public class SelectedCourseServiceImpl extends ServiceImpl<SelectedCourseMapper, SelectedCourse> implements SelectedCourseService {
    @Resource
    private SelectedCourseMapper selectedCourseMapper;

    @Override
    public PageSelectedCourseResponse pageSelectedCourseList(PageSelectedCourseRequest request) {
        QueryWrapper<SelectedCourse> selectedCourseQueryWrapper = new QueryWrapper<>();
        Page<Object> page = PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<SelectedCourse> selectedCourseList = selectedCourseMapper.selectList(selectedCourseQueryWrapper);
        return new PageSelectedCourseResponse().setSelectedCourseList(selectedCourseList)
                .setTotalSize(page.getTotal());
    }
}
