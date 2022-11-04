package com.jj.stu.attendance.dao.model;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Date;

public class Course implements Serializable {
    private Integer id;

    @ApiModelProperty(value = "课程名字")
    private String name;

    @ApiModelProperty(value = "教师Id")
    private Integer teacherId;

    @ApiModelProperty(value = "课程时间")
    private Date courseDate;

    @ApiModelProperty(value = "选课人数")
    private Integer selectedNum;

    @ApiModelProperty(value = "最大人数")
    private Integer maxNum;

    private String info;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Date getCourseDate() {
        return courseDate;
    }

    public void setCourseDate(Date courseDate) {
        this.courseDate = courseDate;
    }

    public Integer getSelectedNum() {
        return selectedNum;
    }

    public void setSelectedNum(Integer selectedNum) {
        this.selectedNum = selectedNum;
    }

    public Integer getMaxNum() {
        return maxNum;
    }

    public void setMaxNum(Integer maxNum) {
        this.maxNum = maxNum;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", teacherId=").append(teacherId);
        sb.append(", courseDate=").append(courseDate);
        sb.append(", selectedNum=").append(selectedNum);
        sb.append(", maxNum=").append(maxNum);
        sb.append(", info=").append(info);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}