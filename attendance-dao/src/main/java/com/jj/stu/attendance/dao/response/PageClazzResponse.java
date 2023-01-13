package com.jj.stu.attendance.dao.response;

import com.jj.stu.attendance.dao.model.Clazz;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageClazzResponse {

    List<Clazz> clazzList;

    Long totalSize;
}
