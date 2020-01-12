package com.ilme.t19.t1909;

import com.ilme.utils.TestUtil;

import java.io.File;
import java.io.PrintStream;

/**
 * 打印流
 *
 * @author yuwenkai
 * @date 2019-09-29 10:48
 **/
public class T291048 {

    public static void main(String[] args) throws Throwable {
        File file = TestUtil.createFile("test", "txt");
        PrintStream printStream = new PrintStream(file);

        PrintStream out = System.out;

        System.setOut(printStream);
        System.out.println("测试");

        System.setOut(out);

        if (TestUtil.prompt("删除测试文件？")) {
            TestUtil.delFile(file);
        }
    }

}
