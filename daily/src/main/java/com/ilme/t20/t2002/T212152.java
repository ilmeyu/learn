package com.ilme.t20.t2002;

import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.types.RedisClientInfo;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * spring-data-redis操作
 *
 * @author ilme
 * @date 2020/2/21 9:52 下午
 **/
@Slf4j
@Setter
public class T212152 {

	RedisTemplate<String, String> redisTemplate;

	final static String SPRING_CONFIG = "t202002/T212152_applicationContext.xml";

	public static void main(String[] args) {
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(SPRING_CONFIG);

		T212152 t212152 = applicationContext.getBean(T212152.class);

		t212152.test();

		t212152.testString();

		t212152.testList();

		t212152.testHash();

		t212152.testSet();

		t212152.testZSet();
	}

	void testZSet() {
		redisTemplate.boundZSetOps("zset").add("z1", 100);
		redisTemplate.boundZSetOps("zset").add("z2", 1000);
		redisTemplate.boundZSetOps("zset").add("z3", 10);
		Set<String> zset = redisTemplate.boundZSetOps("zset").reverseRange(0, -1);

		log.info("zset: {}", zset);
	}

	void testSet() {
		redisTemplate.boundSetOps("set").add("100");
		redisTemplate.boundSetOps("set").add("10000");
		redisTemplate.boundSetOps("set").add("1000");
		Set<String> set = redisTemplate.boundSetOps("set").members();

		log.info("set: {}", set);
	}

	void testHash() {
		redisTemplate.boundHashOps("hash").put("k1", "V1");
		redisTemplate.boundHashOps("hash").put("k3", "V3");
		redisTemplate.boundHashOps("hash").put("k2", "V2");
		Map<String, String> map = redisTemplate.<String, String>boundHashOps("hash").entries();

		log.info("hash: {}", map);
	}

	void testList() {
		redisTemplate.boundListOps("list").rightPush("001");
		redisTemplate.boundListOps("list").rightPush("002");
		List<String> list = redisTemplate.boundListOps("list").range(0, -1);

		log.info("list: {}", list);
	}

	void testString() {
		redisTemplate.boundValueOps("name").set("测试字符串类型");
		Object val = redisTemplate.boundValueOps("name").get();
		log.info("val: {}", val);
	}

	void test() {
		List<RedisClientInfo> clientList = redisTemplate.getClientList();
		if (null != clientList && !clientList.isEmpty()) {
			clientList.forEach((e) -> log.info("{}", e));
		}
	}

}
