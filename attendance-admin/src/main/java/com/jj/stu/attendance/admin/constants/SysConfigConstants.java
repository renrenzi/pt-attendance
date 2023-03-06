package com.jj.stu.attendance.admin.constants;

/**
 * blog 配置类枚举
 *
 * @author 任人子
 */
public enum SysConfigConstants {

    DEFAULT_CATEGORY("1", "默认分类"),
    DEFAULT_TAG("1", "默认标签");

    private final String configField;
    private final String configName;

    SysConfigConstants(String configField, String configName) {
        this.configField = configField;
        this.configName = configName;
    }

    public String getConfigField() {
        return configField;
    }

    public String getConfigName() {
        return configName;
    }
}
