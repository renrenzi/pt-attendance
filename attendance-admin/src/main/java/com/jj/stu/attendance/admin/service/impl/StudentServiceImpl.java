package com.jj.stu.attendance.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jj.stu.attendance.admin.service.StudentService;
import com.jj.stu.attendance.dao.mapper.AdminMapper;
import com.jj.stu.attendance.dao.mapper.StudentMapper;
import com.jj.stu.attendance.dao.model.Admin;
import com.jj.stu.attendance.dao.model.Student;
import com.jj.stu.attendance.meta.dto.AdminDTO;
import com.jj.stu.attendance.meta.dto.StudentDTO;
import com.jj.stu.attendance.meta.request.PageStudentRequest;
import com.jj.stu.attendance.meta.request.StudentBatchInsertRequest;
import com.jj.stu.attendance.meta.request.StudentUpdateRequest;
import com.jj.stu.attendance.meta.response.PageStudentResponse;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * 学生服务impl
 *
 * @author 任人子
 * @date 2022/11/4  - {TIME}
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {
    @Resource
    private StudentMapper studentMapper;
    @Resource
    private AdminMapper adminMapper;
    @Override
    public PageStudentResponse pageStudentList(PageStudentRequest request) {
        Page<Object> page = PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Student> studentList = studentMapper.selectList(new QueryWrapper<>());
        List<Integer> adminIds = studentList.stream().map(Student::getAdminId).collect(Collectors.toList());
        Map<Integer, Admin> adminIdToInfoMap = new HashMap<>();
        if (!CollectionUtils.isEmpty(adminIds)) {
            adminIdToInfoMap = adminMapper.selectList(new QueryWrapper<Admin>().lambda().in(Admin::getId, adminIds)).stream().collect(Collectors.toMap(Admin::getId, Function.identity()));
        }
        List<StudentDTO> studentDTOList = Lists.newArrayList();
        for (Student student: studentList) {
            StudentDTO studentDTO = new StudentDTO();
            BeanUtils.copyProperties(student, studentDTO);

            AdminDTO adminDTO = new AdminDTO();
            BeanUtils.copyProperties(adminIdToInfoMap.getOrDefault(student.getAdminId(), new Admin()), adminDTO);
            studentDTO.setAdminDTO(adminDTO);
            studentDTOList.add(studentDTO);
        }
        return new PageStudentResponse()
                .setStudentList(studentDTOList)
                .setTotalSize(page.getTotal());
    }

    @Override
    public void batchAddStudent(StudentBatchInsertRequest request) {
        // TODO 后续优化
        for(Student student : request.getStudentList()){
            student.setCreateDate(new Date());
            studentMapper.insertSelective(student);
        }
    }

    @Override
    public void updateStudentInfo(StudentUpdateRequest request) {
        Student student = Student.builder()
                .id(request.getId())
                .nickName(request.getNickName())
                .sex(request.getSex())
                .clazzId(request.getClazzId())
                .mobile(request.getMobile())
                .build();
        if (request.getId() == null) {
            studentMapper.insert(student);
            return;
        }
        studentMapper.updateById(student);
    }

    @Override
    public void batchDeleteStudentList(List<Integer> studentIds) {
        studentMapper.deleteBatchIds(studentIds);
    }
}
