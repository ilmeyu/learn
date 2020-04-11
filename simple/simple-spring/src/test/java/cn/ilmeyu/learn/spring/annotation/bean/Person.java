package cn.ilmeyu.learn.spring.annotation.bean;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author 余文楷
 * @date 2020/4/4 9:51 下午
 * @since 1.0.0
 **/
@Slf4j
public class Person {

	String name;

	Integer age;

	Person() {

	}

	public Person(String name, Integer age) {
		this.name = name;
		this.age = age;
	}

	void init() {
		log.debug("{}初始化完成...", this);
	}

	void destroy() {
		log.debug("{}已销毁...", this);
	}

}
