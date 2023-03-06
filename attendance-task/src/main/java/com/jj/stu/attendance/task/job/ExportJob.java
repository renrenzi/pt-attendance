package com.jj.stu.attendance.task.job;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Component;

/**
 * @author zhangjunjie
 * @description
 */
@Slf4j
@Component
@EnableScheduling
public class ExportJob {


    public void executeExport() throws JobExecutionException {
        // your logics
        // log.info("Export Job执行时间: " + new Date());
    }
}
