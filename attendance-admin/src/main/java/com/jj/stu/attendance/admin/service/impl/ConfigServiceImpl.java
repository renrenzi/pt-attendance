package com.jj.stu.attendance.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jj.stu.attendance.admin.service.ConfigService;
import com.jj.stu.attendance.dao.mapper.ConfigMapper;
import com.jj.stu.attendance.dao.model.Config;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 系统配置ServiceImpl
 *
 * @author 任人子
 */
@Slf4j
@Service
public class ConfigServiceImpl extends ServiceImpl<ConfigMapper, Config> implements ConfigService {


}
