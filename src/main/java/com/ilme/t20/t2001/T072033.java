package com.ilme.t20.t2001;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.Comparator;
import java.util.List;

/**
 * 排序
 *
 * @author ilme
 * @date 2020/1/7 8:33 下午
 **/
@Slf4j
@Test
public class T072033 {

	public void test() {
		List<String> list = ImmutableList.of("ACO", "AVL", "B", "C");

		log.info("原始数据: {}", list);

		Ordering<String> natural = Ordering.natural();

		log.info("自然排序: {}", natural.sortedCopy(list));

		Ordering<String> custom = Ordering.from(Comparator.comparingInt(String::length)).reverse();

		log.info("自定义排序[按字符串长度]: {}", custom.sortedCopy(list));

	}
}
