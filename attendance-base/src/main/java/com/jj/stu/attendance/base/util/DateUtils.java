package com.jj.stu.attendance.base.util;

import java.sql.Timestamp;

/**
 * 时间工具类
 *
 * @author 任人子
 * @date 2021/11/11  - {TIME}
 */
public class DateUtils {

    /**
     * 获取本地当前时间
     *
     * @return
     */
    public static Timestamp getLocalCurrentTime() {
        return new Timestamp(System.currentTimeMillis());
    }


}
