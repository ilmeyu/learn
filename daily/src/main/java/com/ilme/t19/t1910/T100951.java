package com.ilme.t19.t1910;

import com.ilme.utils.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.internal.collections.Ints;

import java.util.List;

/**
 * 求List交集
 *
 * @author yuwenkai
 * @date 2019-10-10 09:51
 **/
@Slf4j
public class T100951 {

    public static void main(String[] args) {
        int[] intArr = DataUtil.getIntArr(5);

        List<Integer> list_1 = Ints.asList(intArr);
        log.info("集合一：{}", list_1);

        List<Integer> list_2 = Ints.asList(DataUtil.getIntArr(100));
        log.info("集合二：{}", list_2);

        list_1.retainAll(list_2);
        log.info("交集：{}", list_1);
    }
}
