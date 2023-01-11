package com.jj.stu.attendance.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jj.stu.attendance.admin.service.SelectedCourseService;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.dao.dto.SelectedCourseDTO;
import com.jj.stu.attendance.dao.mapper.SelectedCourseMapper;
import com.jj.stu.attendance.dao.model.SelectedCourse;
import com.jj.stu.attendance.dao.request.selectedCourse.ManageSelectedCourseRequest;
import com.jj.stu.attendance.dao.request.selectedCourse.PageSelectedCourseRequest;
import com.jj.stu.attendance.dao.response.selectedCourse.PageSelectedCourseResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public void updateSelectedCourse(ManageSelectedCourseRequest request) {
        SelectedCourse selectedCourse = request.getSelectedCourse();
        if(selectedCourseMapper.selectById(selectedCourse.getId()) == null){
            selectedCourseMapper.insertSelective(selectedCourse);
        }else {
            selectedCourseMapper.updateByPrimaryKeySelective(selectedCourse);
        }
    }

    @Override
    public void batchDeleteSelectedCourseList(List<Integer> selectedCourseIds) {
        int res = selectedCourseMapper.deleteBatchIds(selectedCourseIds);
        if( res != 1){
            throw new ApiException("批量刪除選課列表失敗");
        }
    }

    @Override
    public PageSelectedCourseResponse pageSelectedCourseList(PageSelectedCourseRequest request) {
        QueryWrapper<SelectedCourse> selectedCourseQueryWrapper = new QueryWrapper<>();
        Page<Object> page = PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<SelectedCourse> selectedCourseList = selectedCourseMapper.selectList(selectedCourseQueryWrapper);
        List<Integer> courseIds = selectedCourseList.stream().map(SelectedCourse::getCourseId).collect(Collectors.toList());
        List<Integer> studentIds = selectedCourseList.stream().map(SelectedCourse::getStudentId).collect(Collectors.toList());
        List<SelectedCourseDTO> responseList = new ArrayList<>();
        
        
        return new PageSelectedCourseResponse().setSelectedCourseList(responseList)
                .setTotalSize(page.getTotal());
    }
}
