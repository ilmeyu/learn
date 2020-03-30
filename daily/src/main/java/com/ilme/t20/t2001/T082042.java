package com.ilme.t20.t2001;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

/**
 * 单例模式
 *
 * @author ilme
 * @date 2020/1/8 8:42 下午
 **/
@Test
@Slf4j
public class T082042 {

	public void test() {
		Singleton instance = Singleton.INSTANCE;

		log.info("{}", instance.hashCode());
	}

	enum Singleton {
		INSTANCE;
	}
}
