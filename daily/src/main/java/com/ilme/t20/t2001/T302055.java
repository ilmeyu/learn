package com.ilme.t20.t2001;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * 动态规划 - 最长公共子序列
 *
 * @author ilme
 * @date 2020/1/30 8:55 下午
 **/
@Slf4j
public class T302055 {

	public static void main(String[] args) {
		char source[] = "5foshhhhh".toCharArray();
		char target[] = "fishhhgg".toCharArray();

		int t[][] = new int[target.length][source.length];

		process(t, target, source);

		log.info("[ ]{}", Arrays.toString(source));
		for (int i = 0; i < t.length; i++) {
			log.info("[{}]{}", target[i], t[i]);
		}

		log.info("最长公共子串: {}", max(t));
	}

	public static int max(int arr[][]) {
		int max = arr[0][0];

		for (int[] ints : arr) {
			for (int anInt : ints) {
				if (anInt > max) {
					max = anInt;
				}
			}
		}

		return max;
	}

	public static void process(int t[][], char target[], char source[]) {
		for (int i = 0; i < target.length; i++) {
			for (int j = 0; j < source.length; j++) {
				if (source[j] == target[i]) {
					if (i == 0 || j == 0) {
						t[i][j] = 1;
					} else {
						t[i][j] = t[i - 1][j - 1] + 1;
					}
				} else {
					if (i == 0 && j == 0) {
						t[i][j] = 0;
					} else if (i == 0 && j > 0) {
						t[i][j] = t[i][j - 1];
					} else if (j == 0) {
						t[i][j] = t[i - 1][j];
					} else {
						t[i][j] = t[i - 1][j] > t[i][j - 1] ? t[i - 1][j] : t[i][j - 1];
					}
				}
			}
		}
	}
}
