package com.ilme.t19.t1909;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import com.ilme.utils.DataUtil;
import com.ilme.utils.TestUtil;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * jdk8 lombda表达式 Stream流
 *
 * @author yuwenkai
 * @date 2019-09-29 17:06
 **/
@Slf4j
public class T291706 {

    List<DataUtil.User> users = Arrays.asList(DataUtil.getUsers(1000));

    @Test
    public void doTest() throws Throwable {
        log.info("原始数据：{}", JSON.toJSONString(users));
    }

    @AfterMethod
    void extractIds() {
        List<Long> ids = users.stream()
                              .map(user -> user.getId())
                              .collect(Collectors.toList());

        log.info("提取ids：{}", ids);
    }

    @AfterMethod
    void filter() {
        List<DataUtil.User> result = users.stream()
                                          .filter(user -> user.getAge() > 50)
                                          .collect(Collectors.toList());

        log.info("年龄大于50岁：{}", JSON.toJSONString(result));
    }

    @AfterMethod
    void sort() {
        String time = TestUtil.exeTime(() -> users.sort((u1, u2) -> u1.getAge() - u2.getAge()));
        log.info("排序时间：{}", time);

        log.info("按照年龄从小到大：{}", JSON.toJSONString(users));
    }
}
