package com.jj.stu.attendance.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jj.stu.attendance.admin.service.TeacherService;
import com.jj.stu.attendance.dao.mapper.TeacherMapper;
import com.jj.stu.attendance.dao.model.Teacher;
import org.springframework.stereotype.Service;

/**
 * 教师服务impl
 *
 * @author 张俊杰
 * @date 2022/11/19
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements TeacherService {

}
