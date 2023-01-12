package com.jj.stu.attendance.admin;

import cn.hutool.crypto.digest.DigestUtil;
import org.junit.Test;

public class MD5Test extends BaseTest {

    @Test
    public void testMD5() {
        System.out.println(DigestUtil.md5Hex("123456"));
    }
}
