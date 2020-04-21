package com.ilme.env;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author iwk
 * @date 2020/4/2 11:00 上午
 * @since 1.0.0
 **/
@Slf4j
public class DataUtil {

	final static List<User> USER_LIST;

	static {
		USER_LIST = new ArrayList<>();
		USER_LIST.add(User.builder().id(100000).name("员工: 张三").userType(UserType.STAFF).build());
		USER_LIST.add(User.builder().id(100001).name("经理: 里斯").userType(UserType.MANAGER).build());
		USER_LIST.add(User.builder().id(100002).name("总经理: 李四").userType(UserType.GM).build());
		USER_LIST.add(User.builder().id(100003).name("人事: 王五").userType(UserType.HR).build());
		USER_LIST.add(User.builder().id(100004).name("财务: 赵六").userType(UserType.FINANCE).build());

		log.debug("测试用户初始化完成！");
	}

	public static User getUserByTypeSingleResult(UserType userType) {
		for (User user : USER_LIST) {
			if (userType.equals(user.getUserType())) {
				return user;
			}
		}
		throw new RuntimeException("用户不存在");
	}

	public static User getUserById(Integer id) {
		for (User user : USER_LIST) {
			if (id.equals(user.getId())) {
				return user;
			}
		}
		throw new RuntimeException("用户不存在");
	}

	public static List<User> getUserByType(UserType userType) {
		List<User> users = new ArrayList<>();

		USER_LIST.forEach(user -> {
			if (userType.equals(user.getUserType())) {
				users.add(user);
			}
		});

		return users;
	}

}
