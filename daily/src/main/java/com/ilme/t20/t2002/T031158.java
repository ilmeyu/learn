package com.ilme.t20.t2002;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * jdk动态代理
 *
 * @author ilme
 * @date 2020/2/3 11:58 上午
 **/
@Slf4j
public class T031158 {

	public static void main(String[] args) {
		Bird bird = new Bird();
		Fly fly = (Fly) getProxy(bird);
		fly.fly("老鹰");
	}

	static Object getProxy(Object obj) {
		return Proxy.newProxyInstance(
				obj.getClass().getClassLoader(),
				obj.getClass().getInterfaces(),
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						log.info("jdk代理开始...");
						Object invoke = method.invoke(obj, args);
						log.info("jdk代理结束...");
						return invoke;
					}
				}
		);
	}

	interface Fly {
		void fly(String name);
	}

	static class Bird implements Fly {
		@Override
		public void fly(String name) {
			log.info("鸟：{}在飞", name);
		}
	}
}
