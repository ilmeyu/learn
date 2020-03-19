package com.ilme.t20.t2002;

import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.Callback;
import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * cglib代理
 *
 * @author ilme
 * @date 2020/2/4 11:06 上午
 **/
@Slf4j
public class T041106 {

	public static void main(String[] args) {
		Test test = new Test();

		Enhancer enhancer = new Enhancer();

		enhancer.setSuperclass(Test.class);
		enhancer.setCallback(new MethodInterceptor() {
			@Override
			public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
				log.info("cglib代理开始");
				Object returnVal = method.invoke(test, args);
				log.info("cglib代理结束");
				return returnVal;
			}
		});
		Test proxyTest = (Test) enhancer.create();

		proxyTest.run(888);

		log.info("proxyTest: {}", proxyTest.getClass());
	}

	static class Test {
		void run(Integer i) {
			log.info("Test...num: {}", i);
		}
	}
}
