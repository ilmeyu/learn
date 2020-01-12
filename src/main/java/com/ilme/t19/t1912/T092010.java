package com.ilme.t19.t1912;

import com.ilme.utils.DataUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 多条件过滤
 *
 * @author yuwenkai
 * @date 2019/12/9 8:10 下午
 **/
@Slf4j
public class T092010 {

	public static void main(String[] args) {

		List<DataUtil.User> users = Arrays.asList(
			new DataUtil.User(100, "张三", 12),
			new DataUtil.User(100, "张三", 12),
			new DataUtil.User(100, "张三", 13),
			new DataUtil.User(100, "张四", 12)
		);

		log.info("before user list: {}", DataUtil.toFormatJson(users));

		users = users.stream().collect(
			Collectors.collectingAndThen(
				Collectors.toCollection(() -> new TreeSet<>((o1, o2) -> Objects.equals(o1.getName(), o2.getName()) && Objects.equals(o1.getAge(), o2.getAge()) ? 0 : -1)),
				ArrayList::new
			)
		);

		log.info("after user list: {}", DataUtil.toFormatJson(users));
	}
}
