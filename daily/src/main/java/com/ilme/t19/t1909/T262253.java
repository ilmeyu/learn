package com.ilme.t19.t1909;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import com.ilme.utils.DataUtil;
import com.ilme.utils.TestUtil;

import java.util.Arrays;

/**
 * 排序算法速度对比
 *
 * @author yuwenkai
 * @date 2019-09-26 22:53
 **/
@Slf4j
public class T262253 {

    static int [] arr = DataUtil.getIntArr(10000000);

    @Test
    public void doTest() {

        log.info("快速排序：{}ns", TestUtil.exeTime(() -> Arrays.sort(arr)));
        log.info("并行排序：{}ns", TestUtil.exeTime(() -> Arrays.parallelSort(arr)));
    }

}
