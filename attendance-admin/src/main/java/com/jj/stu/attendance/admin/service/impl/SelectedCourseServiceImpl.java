package com.jj.stu.attendance.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jj.stu.attendance.admin.service.SelectedCourseService;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.dao.dto.SelectedCourseDTO;
import com.jj.stu.attendance.dao.mapper.CourseMapper;
import com.jj.stu.attendance.dao.mapper.SelectedCourseMapper;
import com.jj.stu.attendance.dao.mapper.StudentMapper;
import com.jj.stu.attendance.dao.model.Course;
import com.jj.stu.attendance.dao.model.SelectedCourse;
import com.jj.stu.attendance.dao.model.Student;
import com.jj.stu.attendance.meta.request.ManageSelectedCourseRequest;
import com.jj.stu.attendance.meta.request.PageSelectedCourseRequest;
import com.jj.stu.attendance.meta.response.PageSelectedCourseResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private CourseMapper courseMapper;
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
        List<Integer> courseIds = selectedCourseList.stream().map(SelectedCourse::getCourseId).distinct().collect(Collectors.toList());
        List<Integer> studentIds = selectedCourseList.stream().map(SelectedCourse::getStudentId).distinct().collect(Collectors.toList());
        Map<Integer, Course> courseIdToNameMap = new HashMap<>(courseIds.size());
        Map<Integer, Student> studentIdToNameMap = new HashMap<>(studentIds.size());
        if (!CollectionUtils.isEmpty(courseIds)) {
            courseIdToNameMap = courseMapper.selectList(new QueryWrapper<Course>().lambda().in(Course::getId, courseIds))
                    .stream().collect(Collectors.toMap(Course::getId, Function.identity(), (v2, v1) -> v1));
        }
        if (!CollectionUtils.isEmpty(studentIds)) {
            studentIdToNameMap = studentMapper.selectList(new QueryWrapper<Student>().lambda().in(Student::getId, studentIds))
                    .stream().collect(Collectors.toMap(Student::getId, Function.identity(), (v2, v1) -> v1));
        }
        List<SelectedCourseDTO> responseList = new ArrayList<>();
        for(SelectedCourse selectedCourse: selectedCourseList){
            SelectedCourseDTO selectedCourseDTO = new SelectedCourseDTO();
            BeanUtil.copyProperties(selectedCourse, selectedCourseDTO);
            selectedCourseDTO.setCourseName(courseIdToNameMap.getOrDefault(selectedCourse.getCourseId(), new Course()).getName());
            Student student = studentIdToNameMap.get(selectedCourse.getStudentId());
            if (student != null){
                selectedCourseDTO.setUserName(student.getUsername());
                selectedCourseDTO.setNickName(student.getNickName());
            }
            responseList.add(selectedCourseDTO);
        }
        return new PageSelectedCourseResponse().setSelectedCourseList(responseList)
                .setTotalSize(page.getTotal());
    }
}
