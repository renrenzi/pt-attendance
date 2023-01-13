package com.jj.stu.attendance.admin.service.clazz;

import com.jj.stu.attendance.admin.BaseTest;
import com.jj.stu.attendance.admin.service.ClazzService;
import com.jj.stu.attendance.dao.model.Clazz;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ClazzServiceTest extends BaseTest {

    @Resource
    private ClazzService clazzService;

    @Test
    public void batchAddClazz(){
        List<Clazz> clazzList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Clazz clazz = new Clazz();
            clazz.setId(i);
            clazz.setName("测试专业" +i);
            clazz.setInfo("测试专业详情" + i);
            clazz.setCreateTime(new Date());
            clazz.setCreateUserId(1);
            clazzList.add(clazz);
        }
        clazzService.saveBatch(clazzList);
    }
}
