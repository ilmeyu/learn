package com.ilme.t20.t2002;

import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ilme
 * @date 2020/2/6 4:52 下午
 **/
@Slf4j
public class T061652 {

	public static void main(String[] args) {
		ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();

		map.put("null", null);
	}

}
