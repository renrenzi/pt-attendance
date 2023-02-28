package com.jj.stu.attendance.task.listener;

import com.jj.stu.attendance.task.event.ExportTaskEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

/**
 * @author zhangjunjie
 * @description
 */
@Component
public class ExportTaskListener {

    @EventListener
    public void onApplicationListener(ExportTaskEvent event) {
        System.out.println("export listener start ---");
    }
}