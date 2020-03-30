package com.ilme.t19.t1911;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * Arrays.asList() 放入 null
 *
 * @author yuwenkai
 * @date 2019/11/8 10:02 上午
 **/
@Slf4j
public class T081507 {

	public static void main(String[] args) {

		List<Integer> asList = Arrays.asList(null, null, 1);

		log.info("list: {}", asList);
	}
}
