package com.ilme.t19.t1912;

import lombok.extern.slf4j.Slf4j;

/**
 * 求最大公约数 [错误]
 *
 * @author yuwenkai
 * @date 2019/12/2 5:12 下午
 **/
@Slf4j
public class T021712 {

	public static void main(String[] args) {
		Integer x = 3, y = 3;

		log.info("{}和{}的最大公约数:{}", x, y, gcd(x, y));
	}

	static int gcd(int x, int y){
		return y != 0 ? gcd(y, x%y) : x;
	}
}
