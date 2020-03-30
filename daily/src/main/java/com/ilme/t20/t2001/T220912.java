package com.ilme.t20.t2001;

import com.ilme.utils.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * 递归求数组和
 *
 * @author ilme
 * @date 2020/1/22 9:12 上午
 **/
@Test
@Slf4j
public class T220912 {

	public void test() {
		int[] arr = DataUtil.getIntArr(10);
		log.info("原始数组: {}", arr);

		log.info("求和: {}", sum(arr));
		log.info("元素个数: {}", count(arr));
		log.info("最大数: {}", max(arr));
	}

	public int count(int[] arr) {
		return arr.length == 1 ? 1 : 1 + count(Arrays.copyOfRange(arr, 1, arr.length));
	}

	public int max(int[] arr) {
		int max = arr[0];

		for (int i = 1; i < arr.length; i++) {
			if (max < arr[i]) {
				max = arr[i];
			}
		}

		return max;
	}

	public int sum(int[] arr) {
		return arr.length == 1 ? arr[0] : arr[0] + sum(Arrays.copyOfRange(arr, 1, arr.length));
	}
}
