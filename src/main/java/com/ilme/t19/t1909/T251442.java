package com.ilme.t19.t1909;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.testng.annotations.Test;
import com.ilme.utils.TestUtil;

/**
 * String.split() 对比 StringUtils.split()
 *
 * @author yuwenkai
 * @date 2019-09-25 14:42
 **/
@Slf4j
public class T251442 {

    static long size;

    static String str;

    static {
        size = 50_0000;
        StringBuilder builder = new StringBuilder();
        for (long i = 0; i < size; i++) {
            builder.append("T-");
            builder.append(i);
            builder.append(",");
        }
        str = builder.toString();
    }

    @Test
    public void doTest() {
        log.debug("testString time: {}ns", TestUtil.exeTime(() -> str.split(",")));
        log.debug("testStringUtils time: {}ns", TestUtil.exeTime(() -> StringUtils.split(str, ",")));
    }

}
