package com.jj.stu.attendance.admin.clazz;

import com.jj.stu.attendance.admin.BaseTest;
import com.jj.stu.attendance.admin.service.ClazzService;
import com.jj.stu.attendance.dao.model.Clazz;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

public class ClazzServiceTest extends BaseTest {

    @Resource
    private ClazzService clazzService;

    @Test
    public void batchAddClazz(){
        List<Clazz> clazzList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Clazz clazz = new Clazz();
            clazz.setId(i);
            clazz.setName("测试专业" +i);
            clazz.setInfo("测试专业详情" + i);
            clazzList.add(clazz);
        }
        clazzService.saveBatch(clazzList);
    }
}
