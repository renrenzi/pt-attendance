package com.jj.stu.attendance.admin.constants;

/**
 * 考勤状态枚举
 * @author renrenzi
 */
public enum AttendanceTypeEnum {

    ATTENDANCE(1, "attendance", "出勤"),
    COMPASSIONATE(2, "compassionate", "事假"),
    SICK(3, "sick", "病假"),
    LATE(4, "late", "迟到"),
    EARLY(5, "early", "早退"),
    TRUANCY(6, "truancy", "旷课");

    private Integer code;

    private String type;

    private String message;

    AttendanceTypeEnum(Integer code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }

    public Integer getCode() {
        return code;
    }

    public String getType() {
        return type;
    }

    public String getMessage() {
        return message;
    }

    public static String getAttendanceMessage(String type) {
        AttendanceTypeEnum[] attendanceTypeEnums = AttendanceTypeEnum.values();
        for (AttendanceTypeEnum attribute : attendanceTypeEnums){
            if (attribute.getType().equals(type)){
                return attribute.getMessage();
            }
        }
        return null;
    }
}
