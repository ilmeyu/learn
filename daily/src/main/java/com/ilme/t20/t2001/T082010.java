package com.ilme.t20.t2001;

import lombok.extern.slf4j.Slf4j;

/**
 * 单例模式 - 懒汉式
 * 静态内部类 线程安全
 *
 * @author ilme
 * @date 2020/1/8 8:10 下午
 **/
@Slf4j
public class T082010 {

	static Singleton singleton;

	// 标志多次获取的实例是否一致[无效]
	static boolean flag = true;

	public static void main(String[] args) {

		// 创建多个线程
		for (int i = 0; i < 10; i++) {
			new Thread(() -> flag &= singleton == Singleton.getInstance()).start();
		}

		System.gc();

		// 等待垃圾回收器回收实例[不一定每次都有效]
		while (true);

	}

	static class Singleton {

		private Singleton() {}

		private static class SingletonInstance {
			private static final Singleton SINGLETON = new Singleton();
		}

		public static Singleton getInstance() {
			return SingletonInstance.SINGLETON;
		}
	}
}
