package com.jj.stu.attendance.admin.service.impl;

import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jj.stu.attendance.admin.constants.CommonStatusEnum;
import com.jj.stu.attendance.admin.service.TeacherService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.dao.mapper.AdminMapper;
import com.jj.stu.attendance.dao.mapper.ClazzMapper;
import com.jj.stu.attendance.dao.mapper.TeacherMapper;
import com.jj.stu.attendance.dao.model.Admin;
import com.jj.stu.attendance.dao.model.Clazz;
import com.jj.stu.attendance.dao.model.Teacher;
import com.jj.stu.attendance.meta.dto.TeacherDTO;
import com.jj.stu.attendance.meta.request.ManageTeacherRequest;
import com.jj.stu.attendance.meta.request.PageTeacherRequest;
import com.jj.stu.attendance.meta.request.TeacherAddRequest;
import com.jj.stu.attendance.meta.response.PageTeacherResponse;
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
 * 教师服务impl
 *
 * @author 张俊杰
 * @date 2022/11/19
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

    @Resource
    private TeacherMapper teacherMapper;
    @Resource
    private AdminMapper adminMapper;
    @Resource
    private ClazzMapper clazzMapper;

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Integer addTeacher(TeacherAddRequest request) {
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
        Teacher teacher = Teacher.builder()
                .username(request.getUsername())
                .password(DigestUtil.md5Hex(request.getPassword()))
                .mobile(request.getMobile())
                .sex(request.getSex())
                .clazzId(request.getClazzId())
                .createDate(now)
                .adminId(admin.getId())
                .build();
        result = teacherMapper.insert(teacher);
        if (result != 1) {
            throw new ApiException("学生详情信息创建失败,请联系管理员");
        }
        return teacher.getId();
    }

    @Override
    @Transactional(rollbackFor = {Exception.class})
    public Result batchDeleteUser(List<Integer> teacherIds) {
        List<Teacher> teacherList = teacherMapper.selectTeacherListByIds(teacherIds);
        if (CollectionUtils.isEmpty(teacherIds)) {
            return ResultGenerator.getResultByOk(null);
        }
        List<Integer> adminIds = teacherList.stream().map(Teacher::getAdminId).distinct().collect(Collectors.toList());
        int len = teacherList.size() + adminIds.size();
        int count = adminMapper.deleteBatchIds(adminIds);
        count += teacherMapper.deleteBatchIds(teacherIds);
        if (!Objects.equals(len, count)) {
            return ResultGenerator.getResultByError("批量删除教师信息失败");
        }
        return ResultGenerator.getResultByOk(count);
    }

    @Override
    public void updateTeacherInfo(ManageTeacherRequest request) {
        Teacher teacher = convertToEntity(request);
        int result = 0;
        result += teacherMapper.updateByPrimaryKeySelective(teacher);
        if (result != 1) {
            throw new ApiException("修改教师信息失败，请联系管理员");
        }
    }

    private Teacher convertToEntity(ManageTeacherRequest request) {
        return Teacher.builder()
                .id(request.getId())
                .username(request.getUsername())
                .clazzId(request.getClazzId())
                .sex(request.getSex())
                .mobile(request.getMobile())
                .build();
    }

    @Override
    public PageTeacherResponse pageTeacherList(PageTeacherRequest request) {
        Page<Object> page = PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Teacher> teacherList = teacherMapper.selectList(buildQueryWrapper(request));
        List<TeacherDTO> resultList = new ArrayList<>(teacherList.size());
        if (CollectionUtils.isEmpty(teacherList)) {
            return new PageTeacherResponse(0L, Lists.emptyList());
        }
        List<Integer> clazzIds = teacherList.stream().map(Teacher::getClazzId).collect(Collectors.toList());
        Map<Integer, Clazz> clazzIdToInfoMap = clazzMapper.selectBatchIds(clazzIds).stream().collect(Collectors.toMap(Clazz::getId, Function.identity()));


        teacherList.forEach(teacher -> {
            TeacherDTO teacherDTO = new TeacherDTO();
            BeanUtils.copyProperties(teacher, teacherDTO);
            // TODO 返回封装用户详情(admin)
            Clazz clazz = clazzIdToInfoMap.get(teacher.getClazzId());
            if (Objects.nonNull(clazz)) {
                teacherDTO.setClazzName(clazz.getName());
            }
            resultList.add(teacherDTO);
        });
        return new PageTeacherResponse()
                .setTeacherList(resultList)
                .setTotalSize(page.getTotal());
    }

    private QueryWrapper buildQueryWrapper(PageTeacherRequest request) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        Teacher teacher = request.getTeacher();
        if (teacher != null) {
            if (teacher.getUsername() != null) {
                queryWrapper.lambda().like(Teacher::getUsername, teacher.getUsername());
            }
        }
        queryWrapper.lambda().orderByDesc(Teacher::getCreateDate);
        return queryWrapper;
    }
}
