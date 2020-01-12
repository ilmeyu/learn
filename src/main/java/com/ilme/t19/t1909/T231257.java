package com.ilme.t19.t1909;

import org.testng.annotations.Test;

/**
 * 死递归导致堆栈溢出
 *
 * @author yuwenkai
 * @date 2019-09-23 12:54
 **/
public class T231257 {

    private int stackLength = 1;

    void stackleak() {
        stackLength++;
        stackleak();
    }

    @Test
    public void doTest() {
        stackleak();
    }
}
