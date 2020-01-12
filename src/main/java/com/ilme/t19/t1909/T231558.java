package com.ilme.t19.t1909;


import org.testng.annotations.Test;

/**
 * 垃圾回收器 - 引用计数？
 *
 * @author yuwenkai
 * @date 2019-09-23 15:58
 **/
public class T231558 {

    T231558 instance;

    @Test
    public void doTest() {

        T231558 t1 = new T231558();
        T231558 t2 = new T231558();

        t1.instance = t2;
        t2.instance = t1;

        t1 = null;
        t2 = null;

        System.gc();
    }
}
