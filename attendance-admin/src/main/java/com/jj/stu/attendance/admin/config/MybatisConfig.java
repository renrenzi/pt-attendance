package com.jj.stu.attendance.admin.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * mybatis配置
 * mybatis dao 扫描配置
 *
 * @author 任人子
 * @date 2022/2/24  - {TIME}
 */
@MapperScan({"com.jj.stu.attendance.dao.mapper"})
@EnableTransactionManagement
@Configuration
public class MybatisConfig {
}
