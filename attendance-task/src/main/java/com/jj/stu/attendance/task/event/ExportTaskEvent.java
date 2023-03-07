package com.jj.stu.attendance.task.event;

import org.springframework.context.ApplicationEvent;

import java.util.Date;

/**
 * 导出事件
 *
 * @author 张俊杰
 */
public class ExportTaskEvent extends ApplicationEvent {


    /**
     * 文件url
     */
    private final String url;

    /**
     * 创建时间
     */
    private final Date createTime;

    public ExportTaskEvent(Object source, String url, Date createTime) {
        super(source);
        this.url = url;
        this.createTime = createTime;
    }

    public String getUrl() {
        return url;
    }

    public Date getCreateTime() {
        return createTime;
    }
}

