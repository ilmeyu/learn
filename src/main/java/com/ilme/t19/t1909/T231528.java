package com.ilme.t19.t1909;

import org.testng.annotations.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 非安全分配内存
 *
 * VM Args: -Xms20M-XX : MaxDirectMemorySize=10M
 * @author yuwenkai
 * @date 2019-09-23 15:28
 **/
public class T231528 {

    private static final int _1MB = 1024 * 1024;

    @Test
    public void doTest() throws Throwable {
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];
        unsafeField.setAccessible(true);

        Unsafe unsafe = (Unsafe)unsafeField.get(null);

        while (true) {
            unsafe.allocateMemory(_1MB);
        }
    }
}
