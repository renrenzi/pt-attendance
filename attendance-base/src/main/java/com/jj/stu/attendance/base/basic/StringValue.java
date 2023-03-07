package com.jj.stu.attendance.base.basic;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * 字符串值
 *
 * @author renrenzi
 * @date 2023/01/20
 */
@Data
@AllArgsConstructor
public class StringValue {
    private String value;

    public String getValue() {
        return value;
    }
}
