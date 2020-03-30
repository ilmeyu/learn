package com.ilme.t19.t1909;

import org.testng.annotations.Test;

/**
 * 创建线程导致内存溢出
 *
 * @author yuwenkai
 * @date 2019-09-23 13:06
 **/
public class T231306 {

    private long counter;

    @Test
    public void doTest() {

        while (true) {
            Thread thread = new Thread(() -> {counter++;});
            thread.start();
        }
    }
}
