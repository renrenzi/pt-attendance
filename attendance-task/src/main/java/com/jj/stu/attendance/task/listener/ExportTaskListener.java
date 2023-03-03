package com.jj.stu.attendance.task.listener;

import com.jj.stu.attendance.task.event.ExportTaskEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author zhangjunjie
 * @description
 */
@Component
@Slf4j
public class ExportTaskListener {

    @EventListener
    public void onApplicationListener(ExportTaskEvent event) {
        log.info("export listener start ----------------");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("export listener end ----------------");
    }
}
