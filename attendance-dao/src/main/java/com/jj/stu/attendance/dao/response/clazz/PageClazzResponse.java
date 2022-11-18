package com.jj.stu.attendance.dao.response.clazz;

import com.jj.stu.attendance.dao.model.Clazz;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.List;

@Data
@Accessors(chain = true)
public class PageClazzResponse {

    List<Clazz> clazzList;

    Integer totalSize;
}
