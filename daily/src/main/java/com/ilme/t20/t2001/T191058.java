package com.ilme.t20.t2001;

import lombok.extern.slf4j.Slf4j;

/**
 * 验证虚拟机装载类时是同步加锁的
 *
 * @author ilme
 * @date 2020/1/19 10:58 上午
 **/
@Slf4j
public class T191058 {

	public static void main(String[] args) {
		Runnable runnable = () -> {

			A a = new A();
		};

		new Thread(runnable, "线程A").start();
		new Thread(runnable, "线程B").start();
	}

	static class A {
		static {
			log.info("当前线程: {}", Thread.currentThread().getName());
			if (false) { // 防止编译不通过
				while (true) {
				}
			}
			log.info("{}: 初始化结束!", Thread.currentThread().getName());
		}
	}
}
