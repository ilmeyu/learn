package com.ilme.t20.t2001;

import com.google.common.collect.ImmutableList;
import com.ilme.utils.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

/**
 * 二分查找
 *
 * @author ilme
 * @date 2020/1/19 9:14 下午
 **/
@Test
@Slf4j
public class T192114 {

	public void test() {
		// 创建固定长度集合
		List<Integer> list = ImmutableList.of(1, 4, 6, 111, 978, 1000, 10086);
		// 目标值下标
		Integer index = null;
		// 打印日志
		log.info("原始数组: {}", list);
		// 开始 === 二分查找
		// 需要查找的值
		int target = 11;
		// 当前最小边界
		int low = 0;
		// 当前最大边界
		int heght = list.size();
		// 当前指向下标
		int mid;

		while (low <= heght) {
			mid = (heght + low) / 2;
			if (target == list.get(mid)) {
				index = mid;
				break;
			} else if (target < list.get(mid)) {
				heght = mid - 1;
			} else {
				low = mid + 1;
			}
		}
		// === 结束
		// 日志输出查找到的结果
		log.info("在第{}个", index);
		// === END ===
	}
}
