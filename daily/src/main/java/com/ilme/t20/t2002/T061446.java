package com.ilme.t20.t2002;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * 集合泛型通配符问题
 *
 * @author ilme
 * @date 2020/2/6 2:47 下午
 **/
@Slf4j
public class T061446 {

	public static void main(String[] args) {
		ArrayList<? extends Number> list_1 = new ArrayList<>();
		ArrayList<? super Number> list_2 = new ArrayList<>();

		list_1.add(null);
		Number number = list_1.get(1);

		list_2.add(1);
		Object object = list_2.get(1);
	}

}
