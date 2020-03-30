package com.ilme.t19.t1909;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import com.ilme.utils.DataUtil;

import java.util.Arrays;

/**
 * 冒泡排序
 *
 * @author yuwenkai
 * @date 2019-09-26 22:26
 **/
@Slf4j
public class T262226 {

    static int [] arr = DataUtil.getIntArr(100);

    @Test
    public void doTest() {
        log.info("before: {}", Arrays.toString(arr));
        sort(arr);
        log.info("after: {}", Arrays.toString(arr));
    }

    void sort(int... arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] < arr[j + 1]) {
                    arr[j] = arr[j] ^ arr[j + 1];
                    arr[j + 1] = arr[j] ^ arr[j + 1];
                    arr[j] = arr[j] ^ arr[j + 1];
                }
            }
        }
    }
}
