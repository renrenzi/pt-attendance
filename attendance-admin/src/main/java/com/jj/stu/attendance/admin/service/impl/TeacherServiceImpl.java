package com.jj.stu.attendance.admin.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.jj.stu.attendance.admin.service.TeacherService;
import com.jj.stu.attendance.base.basic.Result;
import com.jj.stu.attendance.base.basic.ResultGenerator;
import com.jj.stu.attendance.base.exception.ApiException;
import com.jj.stu.attendance.dao.mapper.TeacherMapper;
import com.jj.stu.attendance.dao.model.Teacher;
import com.jj.stu.attendance.meta.request.ManageTeacherRequest;
import com.jj.stu.attendance.meta.request.PageTeacherRequest;
import com.jj.stu.attendance.meta.response.PageTeacherResponse;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
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
    private TeacherMapper teacherMapper;

    @Override
    public Result batchDeleteUser(List<Integer> teacherIds) {
        int result = teacherMapper.deleteBatchIds(teacherIds);
        if (result < 0) {
            return ResultGenerator.getResultByError("批量删除教师信息失败");
        }
        return ResultGenerator.getResultByOk(result);
    }

    @Override
    public void updateTeacherInfo(ManageTeacherRequest request) {
        Teacher teacher = convertToEntity(request);
        int result = 0;
        if (request.getId() == null) {
            teacher.setCreateDate(new Date());
            result += teacherMapper.insert(teacher);
        } else {
            result += teacherMapper.updateByPrimaryKey(teacher);
        }
        if (result != 1) {
            throw new ApiException("修改或添加教师失败，请联系管理员");
        }
    }

    private Teacher convertToEntity(ManageTeacherRequest request) {
        return Teacher.builder()
                .id(request.getId())
                .username(request.getUsername())
                .clazzId(request.getClazzId())
                .sex(request.getSex())
                .mobile(request.getMobile())
                .createDate(request.getCreateDate())
                .adminId(request.getAdminId())
                .build();
    }

    @Override
    public PageTeacherResponse pageTeacherList(PageTeacherRequest request) {
        Page<Object> page = PageHelper.startPage(request.getPageNum(), request.getPageSize());
        List<Teacher> teacherList = teacherMapper.selectList(buildQueryWrapper(request));
        //todo 返回封装用户详情(admin)
        return new PageTeacherResponse()
                .setTeacherList(teacherList)
                .setTotalSize(page.getTotal());
    }

    private QueryWrapper buildQueryWrapper(PageTeacherRequest request) {
        QueryWrapper<Teacher> queryWrapper = new QueryWrapper<>();
        Teacher teacher = request.getTeacher();
        if (teacher != null) {
            if (teacher.getId() != null) {
                queryWrapper.lambda().eq(Teacher::getId, teacher.getId());
            }
        }
        queryWrapper.lambda().orderByDesc(Teacher::getCreateDate);
        return queryWrapper;
    }
}
