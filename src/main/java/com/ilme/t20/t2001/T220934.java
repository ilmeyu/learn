package com.ilme.t20.t2001;

import com.ilme.utils.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.Arrays;

/**
 * 递归二分查找
 *
 * @author ilme
 * @date 2020/1/22 9:34 上午
 **/
@Test
@Slf4j
public class T220934 {

	public void test() {
		int[] arr = DataUtil.getIntArr(10);
		int[] backup = Arrays.copyOf(arr, arr.length);
		log.info("生成数据: {}", arr);
		Arrays.sort(arr);
		log.info("排序: {}", arr);
		log.info("普通二分查找、找数字: {}, 在第{}个索引位置!", backup[0], binarySearch(backup[0], arr));
		log.info("递归版二分查找、找数字: {}, 在第{}个索引位置!", backup[0], binarySearch(backup[0], arr, 0, arr.length - 1));
	}

	public int binarySearch(int dist, int[] arr, int start, int end) {
		int mid = (start + end) / 2;
		if (dist == arr[mid]) {
			return mid;
		} else if (dist < arr[mid]) {
			return binarySearch(dist, arr, start, mid - 1);
		} else {
			return binarySearch(dist, arr, mid + 1, end);
		}
	}

	public int binarySearch(int dist, int[] arr) {
		int low = 0;
		int height = arr.length - 1;
		int mid = -1;

		while (low <= height) {
			mid = (low + height) / 2;
			if (dist == arr[mid]) {
				return mid;
			} else if (dist < arr[mid]) {
				height = mid - 1;
			} else {
				low = mid + 1;
			}
		}

		return mid;
	}
}
