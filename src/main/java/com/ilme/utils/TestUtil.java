package com.ilme.utils;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Objects;
import java.util.Scanner;
import java.util.function.Supplier;

/**
 * 测试工具
 *
 * @author yuwenkai
 * @date 2019-09-26 22:41
 **/
@Slf4j
public class TestUtil {

    /**
     * 确认提示
     *
     * @param msg 提示信息
     */
    public static boolean prompt(String msg) {
        Scanner input = new Scanner(System.in);
        System.out.println(msg);
        System.out.print("输入1确认：");
        boolean isOk = Objects.equals(input.next(), "1");
        input.close();
        return isOk;
    }

    /**
     * 创建测试文件
     *
     * @param fileName 文件名字
     * @param fileType 文件类型
     */
    public static File createFile(String fileName, String fileType) {
        File file = new File("/Users/chen/Desktop/" + fileName + "." + fileType);
        try {
            if (file.exists()) {
                file.createNewFile();
            } else {
                return file;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (file.exists()) {
            return file;
        } else {
            throw new RuntimeException("文件创建失败！");
        }
    }

    /**
     * 删除文件
     *
     * @param file 目标文件
     */
    public static boolean delFile(File file) {
        if (file == null || !file.exists()) {
            return true;
        }
        file.delete();
        return file.exists();
    }

//    public static <T> void run(<T> supplier) {
//        supplier.
//    }

    /**
     * 统计执行时间
     * @param timer 计时接口
     * @return 执行时间
     */
    public static String exeTime(Timer timer) {
        return timer.timing();
    }

    @FunctionalInterface
    public interface Timer {

        void exe();

        default String timing() {
            long startTime = System.nanoTime();
            exe();
            long endTime = System.nanoTime();
            NumberFormat nf = NumberFormat.getInstance();
            nf.setGroupingUsed(false);
            return nf.format(BigDecimal.valueOf(endTime - startTime).doubleValue());
        }
    }
}
