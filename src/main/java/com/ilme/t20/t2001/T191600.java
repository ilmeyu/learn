package com.ilme.t20.t2001;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;
import sun.launcher.resources.launcher_zh_CN;

import java.lang.reflect.Method;

/**
 * @author ilme
 * @date 2020/1/19 4:00 下午
 **/
@Test
@Slf4j
public class T191600 {

	public void test() throws Exception {
		ClassLoader classLoader = ClassLoader.getSystemClassLoader();
		log.info("{}", classLoader);

		ClassLoader parent = classLoader.getParent();

		log.info("{}", parent);

		ClassLoader loader = parent.getParent();

		log.info("{}", loader);

	}
}
