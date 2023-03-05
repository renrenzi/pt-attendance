package com.jj.stu.attendance.task.config;

import com.jj.stu.attendance.task.job.ExportJob;
import com.jj.stu.attendance.task.job.ImportJob;
import org.quartz.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

import java.util.Date;

/**
 * @author zhangjunjie
 * @description
 */
@Configuration
public class QuartzConfig {

    /**
     * 配置导出任务的任务实例
     */
    @Bean(name = "exportJobDetail")
    public MethodInvokingJobDetailFactoryBean exportJobDetail(ExportJob exportJob) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(false);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(exportJob);
        // 需要执行的方法
        jobDetail.setTargetMethod("executeExport");
        return jobDetail;
    }


    /**
     *  配置导出触发器
     */
    @Bean(name = "exportTrigger")
    public SimpleTriggerFactoryBean exportTrigger(JobDetail exportJobDetail) {
        SimpleTriggerFactoryBean trigger = new SimpleTriggerFactoryBean();
        trigger.setJobDetail(exportJobDetail);
        // 设置任务启动延迟
        trigger.setStartDelay(0);
        // 设置定时任务启动时间
        trigger.setStartTime(new Date());
        // 每5秒执行一次
        trigger.setRepeatInterval(5000);
        return trigger;
    }

    /**
     * 配置导入任务的任务实例
     */
    @Bean(name = "importJobDetail")
    public MethodInvokingJobDetailFactoryBean importJobDetail(ImportJob importJob) {
        MethodInvokingJobDetailFactoryBean jobDetail = new MethodInvokingJobDetailFactoryBean();
        // 是否并发执行
        jobDetail.setConcurrent(true);
        // 为需要执行的实体类对应的对象
        jobDetail.setTargetObject(importJob);
        // 需要执行的方法
        jobDetail.setTargetMethod("executeInternal");
        return jobDetail;
    }


    /**
     *  配置导入触发器
     */
    @Bean(name = "importTrigger")
    public CronTriggerFactoryBean importTrigger(JobDetail importJobDetail) {
        CronTriggerFactoryBean trigger = new CronTriggerFactoryBean();
        trigger.setJobDetail(importJobDetail);
        // 设置定时任务启动时间
        trigger.setStartTime(new Date());
        // cron表达式
        trigger.setCronExpression("*/5 * * * * ?");
        return trigger;
    }


    /**
     * 配置 trigger
     */
    @Bean(name = "scheduler")
    public SchedulerFactoryBean schedulerFactory(Trigger exportTrigger, Trigger importTrigger) {
        SchedulerFactoryBean bean = new SchedulerFactoryBean();
        // 延时启动，应用启动1秒后
        bean.setStartupDelay(1);
        // 注册触发器
        bean.setTriggers(exportTrigger, importTrigger);
        return bean;
    }
}
