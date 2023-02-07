package com.jj.stu.attendance.admin.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jj.stu.attendance.admin.service.SelectedCourseService;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.meta.dto.SelectedCourseDTO;
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
import org.springframework.transaction.annotation.Transactional;
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
    @Transactional(rollbackFor = {Exception.class})
    public void updateSelectedCourse(ManageSelectedCourseRequest request) {
        // 检查参数是否有效
        checkRequestParamsValid(request);
        SelectedCourse selectedCourse = SelectedCourse.builder()
                .courseId(request.getCourseId())
                .studentId(request.getStudentId())
                .id(request.getId())
                .build();
        Map<Integer, Course> courseIdToInfoMap = courseMapper.selectList(new QueryWrapper<>()).stream().collect(Collectors.toMap(Course::getId, Function.identity(), (v2, v1) -> v1));
        // 当前选课数 + 1
        judgeNumberOfCoursesSelected(courseIdToInfoMap, request.getCourseId());
        if(selectedCourseMapper.selectById(selectedCourse.getId()) == null){
            // 添加之前查询是否已选
            SelectedCourse existSelectedCourse = selectedCourseMapper.selectOne(new QueryWrapper<SelectedCourse>().lambda().eq(SelectedCourse::getCourseId, request.getCourseId())
                    .eq(SelectedCourse::getStudentId, request.getStudentId()));
            if (existSelectedCourse != null){
                throw new ApiException("学生已选该课程");
            }
            selectedCourseMapper.insertSelective(selectedCourse);
        }else {
            // 之前选课数 - 1
            Course oldCourse = courseIdToInfoMap.get(request.getOldCourseId());
            oldCourse.setSelectedNum(oldCourse.getSelectedNum() - 1);
            courseMapper.updateByPrimaryKeySelective(oldCourse);
            selectedCourseMapper.updateByPrimaryKeySelective(selectedCourse);
        }
    }
    private void checkRequestParamsValid(ManageSelectedCourseRequest request){
        Student student = studentMapper.selectOne(new QueryWrapper<Student>().lambda().eq(Student::getId, request.getStudentId()));
        if (student == null) {
            throw new ApiException("当前学生无效, 选课操作失败");
        }
        List<Integer> courseIds = new ArrayList<Integer>(){{
            add(request.getCourseId());
            add(request.getOldCourseId());
        }};
        List<Course> courseList = courseMapper.selectByList(courseIds);
        if (courseList == null || courseIds.size() != courseList.size()) {
            throw new ApiException("所选课程无效, 选课操作失败");
        }
    }
    /**
     * 判断所选课程数量
     */
    private void judgeNumberOfCoursesSelected(Map<Integer, Course> courseIdToInfoMap, Integer courseId){
        Course currentCourse = courseIdToInfoMap.get(courseId);
        if (currentCourse.getSelectedNum() + 1 > currentCourse.getMaxNum()){
            throw new ApiException("课程:" +currentCourse.getName() + "的选课数量已到最大值");
        }
        currentCourse.setSelectedNum(currentCourse.getSelectedNum() + 1);
        courseMapper.updateByPrimaryKeySelective(currentCourse);
    }
    @Override
    public void batchDeleteSelectedCourseList(List<Integer> selectedCourseIds) {
        List<Integer> courseIds = selectedCourseMapper.selectBatchIds(selectedCourseIds).stream().map(SelectedCourse::getCourseId).distinct().collect(Collectors.toList());
        if (CollectionUtils.isEmpty(courseIds)) {
            return;
        }
        Map<Integer, Course> courseIdToItemMap = courseMapper.selectBatchIds(courseIds).stream().collect(Collectors.toMap(Course::getId, Function.identity(), (v2, v1) -> v1));
        for (Integer courseId : courseIds) {
            Course course = courseIdToItemMap.get(courseId);
            course.setSelectedNum(course.getSelectedNum() - 1);
            courseMapper.updateByPrimaryKey(course);
        }
        int res = selectedCourseMapper.deleteBatchIds(selectedCourseIds);
        if( res != 1){
            throw new ApiException("批量刪除選課列表失敗");
        }
    }

    @Override
    public PageSelectedCourseResponse pageSelectedCourseList(PageSelectedCourseRequest request) {
        QueryWrapper<SelectedCourse> selectedCourseQueryWrapper = new QueryWrapper<>();
        if (request.getSelectedCourse() != null) {
            SelectedCourse selectedCourse = request.getSelectedCourse();
            if (selectedCourse.getStudentId() != null){
                selectedCourseQueryWrapper.lambda().eq(SelectedCourse::getStudentId, selectedCourse.getStudentId());
            }
            if (selectedCourse.getCourseId() != null){
                selectedCourseQueryWrapper.lambda().eq(SelectedCourse::getCourseId, selectedCourse.getCourseId());
            }
        }
        selectedCourseQueryWrapper.lambda().orderByDesc(SelectedCourse::getId);
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
            Course course = courseIdToNameMap.get(selectedCourse.getCourseId());
            if (course != null){
                selectedCourseDTO.setCourseName(course.getName());
                selectedCourseDTO.setSelectedNum(course.getSelectedNum());
                selectedCourseDTO.setMaxNum(course.getMaxNum());
            }
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
