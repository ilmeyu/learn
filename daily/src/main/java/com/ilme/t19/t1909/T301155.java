package com.ilme.t19.t1909;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

/**
 * 判断对象是否是一个类的实例
 *
 * @author yuwenkai
 * @date 2019-09-30 11:55
 **/
@Slf4j
public class T301155 {

    @Test
    public void doTest() {
        log.info("Integer 是 Number 的实例：{}", Integer.valueOf(10086) instanceof Number);
    }
}
