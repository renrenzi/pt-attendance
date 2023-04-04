package com.jj.stu.attendance.admin.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jj.stu.attendance.admin.constants.CommonStatusEnum;
import com.jj.stu.attendance.admin.service.StudentService;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.dao.mapper.AdminMapper;
import com.jj.stu.attendance.dao.mapper.ClazzMapper;
import com.jj.stu.attendance.dao.mapper.StudentMapper;
import com.jj.stu.attendance.dao.model.Admin;
import com.jj.stu.attendance.dao.model.Clazz;
import com.jj.stu.attendance.dao.model.Student;
import com.jj.stu.attendance.meta.dto.AdminDTO;
import com.jj.stu.attendance.meta.dto.StudentDTO;
import com.jj.stu.attendance.meta.request.PageStudentRequest;
import com.jj.stu.attendance.meta.request.StudentBatchInsertRequest;
import com.jj.stu.attendance.meta.request.StudentInsertRequest;
import com.jj.stu.attendance.meta.request.StudentUpdateRequest;
import com.jj.stu.attendance.meta.response.PageStudentResponse;
import org.assertj.core.util.Lists;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.*;
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
    @Resource
    private ClazzMapper clazzMapper;

    @Override
    public PageStudentResponse pageStudentList(PageStudentRequest request) {
        Page<Object> page = PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Student> studentList = studentMapper.selectList(buildQueryWrapper(request));
        if (CollectionUtils.isEmpty(studentList)) {
            return new PageStudentResponse(0L, null);
        }
        List<Integer> adminIds = studentList.stream().map(Student::getAdminId).collect(Collectors.toList());
        List<Integer> clazzIds = studentList.stream().map(Student::getClazzId).distinct().collect(Collectors.toList());
        Map<Integer, Admin> adminIdToInfoMap = new HashMap<>();
        Map<Integer, Clazz> clazzIdToInfoMap = new HashMap<>();

        if (!CollectionUtils.isEmpty(adminIds)) {
            adminIdToInfoMap = adminMapper.selectList(new QueryWrapper<Admin>().lambda()
                    .in(Admin::getId, adminIds)
                    .eq(Admin::getState, CommonStatusEnum.ENABLE.getStatus())
            ).stream().collect(Collectors.toMap(Admin::getId, Function.identity()));
        }
        if (!CollectionUtils.isEmpty(clazzIds)) {
            clazzIdToInfoMap = clazzMapper.selectList(new QueryWrapper<Clazz>().lambda()
                    .in(Clazz::getId, clazzIds)
                    .eq(Clazz::getState, CommonStatusEnum.ENABLE.getCode())
            ).stream().collect(Collectors.toMap(Clazz::getId, Function.identity()));
        }

        List<StudentDTO> studentDTOList = Lists.newArrayList();
        for (Student student : studentList) {
            StudentDTO studentDTO = new StudentDTO();
            BeanUtils.copyProperties(student, studentDTO);
            // 设置用户相关信息
            AdminDTO adminDTO = new AdminDTO();
            BeanUtils.copyProperties(adminIdToInfoMap.getOrDefault(student.getAdminId(), new Admin()), adminDTO);
            studentDTO.setAdminDTO(adminDTO);
            // 设置专业名称
            studentDTO.setClazzName(clazzIdToInfoMap.getOrDefault(student.getClazzId(), new Clazz()).getName());
            studentDTOList.add(studentDTO);
        }
        return new PageStudentResponse()
                .setStudentList(studentDTOList)
                .setTotalSize(page.getTotal());
    }

    private QueryWrapper<Student> buildQueryWrapper(PageStudentRequest request) {
        Student student = request.getStudent();
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.lambda().orderByDesc(Student::getCreateDate);
        if (Objects.isNull(student) || StrUtil.isBlank(student.getNickName())) {
            return wrapper;
        }
        wrapper.lambda().like(Student::getNickName, student.getNickName());
        return wrapper;
    }

    @Override
    public void batchAddStudent(StudentBatchInsertRequest request) {
        // TODO 后续优化
        for (Student student : request.getStudentList()) {
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
                .username(request.getUsername())
                .clazzId(request.getClazzId())
                .mobile(request.getMobile())
                .build();
        int result = studentMapper.updateByPrimaryKeySelective(student);
        if (result != 1) {
            throw new ApiException("修改学生信息失败");
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public void batchDeleteStudentList(List<Integer> studentIds) {
        List<Student> studentList = studentMapper.selectBatchIds(studentIds);
        if (CollectionUtils.isEmpty(studentList)) {
            return;
        }
        List<Integer> adminIds = studentList.stream().map(Student::getAdminId).distinct().collect(Collectors.toList());
        int len = studentIds.size() + adminIds.size();
        int count = adminMapper.deleteBatchIds(adminIds);
        count += studentMapper.deleteBatchIds(studentIds);
        if (!Objects.equals(len, count)) {
            throw new ApiException("批量删除学生信息失败");
        }
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Integer addStudent(StudentInsertRequest request) {
        Date now = new Date();
        Clazz clazz = clazzMapper.selectByPrimaryKey(request.getClazzId());
        if (Objects.isNull(clazz)) {
            throw new ApiException("专业不存在，请重新选择");
        }
        Admin admin = new Admin()
                .setState(CommonStatusEnum.ENABLE.getCode())
                .setCreateUser(request.getCreateUserName())
                .setCreateTime(now)
                .setUserName(request.getUsername())
                .setPassWord(DigestUtil.md5Hex(request.getPassword()));
        int result = adminMapper.insert(admin);
        if (result != 1) {
            throw new ApiException("学生账号创建失败,请联系管理员");
        }
        Student student = Student.builder()
                .username(request.getUsername())
                .password(DigestUtil.md5Hex(request.getPassword()))
                .nickName(request.getNickName())
                .mobile(request.getMobile())
                .sex(request.getSex())
                .clazzId(request.getClazzId())
                .createDate(now)
                .adminId(admin.getId())
                .build();
        result = studentMapper.insert(student);
        if (result != 1) {
            throw new ApiException("学生详情信息创建失败,请联系管理员");
        }
        return student.getId();
    }
}
