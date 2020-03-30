package com.ilme.t19.t1910;

import com.ilme.utils.DataUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 二分查找
 *
 * @author yuwenkai
 * @date 2019-10-10 09:51
 **/
@Slf4j
public class T221549 {

	public static void main(String[] args) {
		int[] arr = DataUtil.getIntArr(100);
		Arrays.sort(arr);
		log.info("show: {}", arr);

		int index = binarySearch(arr, 1);
		log.info("result: arr[{}] = {}", index, arr[index]);
	}

	static int binarySearch(int[] arr, int item) {
		int low = 0;
		int hign = arr.length - 1;

		while (low <= hign) {
			int mid = (low + hign) / 2;
			if (item == arr[mid]) {
				return mid;
			}
			if (item < arr[mid]) {
				hign = mid - 1;
			}
			if (item > arr[mid]) {
				low = mid + 1;
			}
		}
		return -1;
	}
}
