package com.ilme.t19.t1909;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

/**
 * 常量池
 *
 * @author yuwenkai
 * @date 2019-09-23 13:46
 **/
@Slf4j
public class T231346 {

    @Test
    public void doTest() {

        String str1 = new StringBuilder("咖").append("啡").toString();
        log.debug("RES1: {}", str1 == str1.intern());

        String str2 = new StringBuilder("ja").append("va").toString();
        log.debug("RES2: {}", str2 == str2.intern());
    }
}
