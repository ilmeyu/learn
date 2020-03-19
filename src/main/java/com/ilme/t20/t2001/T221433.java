package com.ilme.t20.t2001;

import com.ilme.utils.DataUtil;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Queue;

/**
 * 快速排序
 *
 * @author ilme
 * @date 2020/1/22 2:33 下午
 **/
@Slf4j
public class T221433 {

	public static void main(String[] args) {
		int[] arr = DataUtil.getIntArr(1000000);
		log.info("生成数组: \t{}", arr);
		quickSort(arr);
		log.info("快排后: \t{}", arr);
	}

	public static void quickSort(int[] arr) {
		quickSort(arr, 0, arr.length - 1);
//		Qsort(arr, 0, arr.length - 1);
	}

	public static void quickSort(int arr[], int low, int high) {
		if (high - low < 1) {
			return;
		}

		int h = high;
		int pivot = low++;

		while (low < high) {
			if (arr[pivot] < arr[low] && arr[pivot] > arr[high]) {
				swap(arr, low, high);
			}
			if (arr[pivot] < arr[low]) {
				high--;
			} else {
				low++;
			}
		}

		if (arr[pivot] > arr[low]) {
			swap(arr, pivot, low);
		}

		quickSort(arr, pivot, low - 1);
		quickSort(arr, low, h);
	}

	static void Qsort(int arr[], int left, int right)
	{
		if(left >= right)
			return;

		int i, j, temp, t;
		i = left;
		j = right;

		temp = arr[i];  /*将基准数放在temp里面*/
		while(i < j)
		{
			while(arr[j] >= temp && i < j)  /*不能没有等于*/
				j--;
			while(arr[i] <= temp && i < j)
				i++;

			if(i < j)   /*交换i,j 需要判断是否i < j;*/
			{
				t = arr[i];
				arr[i] = arr[j];
				arr[j] = t;
			}


		}
		arr[left] = arr[i]; /*基准数交换*/
		arr[i] = temp;

		Qsort(arr, left, i-1);  /*处理枢轴左边，递归*/
		Qsort(arr, i+1, right); /*处理枢轴右边，递归*/

	}

	static void swap(int[] arr, int left, int right) {
		arr[left] = arr[left] ^ arr[right];
		arr[right] = arr[right] ^ arr[left];
		arr[left] = arr[left] ^ arr[right];
	}

}
