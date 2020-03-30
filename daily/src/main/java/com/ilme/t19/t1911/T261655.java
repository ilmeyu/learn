package com.ilme.t19.t1911;

import lombok.extern.slf4j.Slf4j;

/**
 * finally 和 return 执行顺序
 *
 * @author yuwenkai
 * @date 2019/11/26 4:55 下午
 **/
@Slf4j
public class T261655 {

	static boolean flag = true;

	public static void main(String[] args) {
		run();
	}

	static void run() {
		try {
			log.info("start...");   // 1
			int i = 1 / 0;  // 2
		} catch (Exception e) {
			return ;    // 5
		} finally {
			flag = false;   // 3
			log.info("finally");    // 4
		}

		return ;
	}

}
