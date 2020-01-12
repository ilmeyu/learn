package com.ilme.t19.t1911;

import lombok.extern.slf4j.Slf4j;

import java.util.HashSet;
import java.util.Set;

/**
 * Set集合放入重复值
 *
 * @author yuwenkai
 * @date 2019/11/8 10:02 上午
 **/
@Slf4j
public class T081002 {

	public static void main(String[] args) {
		Set<Integer> set = new HashSet<>();
		set.add(1);
		set.add(1);

		log.info("set: {}", set);
	}
}
