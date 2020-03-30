package com.ilme.t19.t1911;

import com.ilme.utils.DataUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 测试 stream().flatMap()
 *
 * @author yuwenkai
 * @date 2019/11/22 11:44 上午
 **/
@Slf4j
public class T221144 {

	public static void main(String[] args) {
		List<DataUtil.User> user1 = Arrays.asList(DataUtil.getUsers(10));
		List<DataUtil.User> user2 = Arrays.asList(DataUtil.getUsers(10));
		Stream<Stream<DataUtil.User>> stream = Stream.of(user1.stream(), user2.stream());

		List<DataUtil.User> users = stream.flatMap(e -> e).collect(Collectors.toList());

		log.info("users: {}", users);

	}
}
