package com.ilme.t20.t2001;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

/**
 * 单例模式 - 懒汉式 错误版
 * 线程安全问题[出现多个实例]
 *
 * @author ilme
 * @date 2020/1/8 3:49 下午
 **/
@Test
@Slf4j
public class T081549 {

	static Singleton singleton;

	// 标志多次获取的实例是否一致[无效]
	static boolean flag = true;

	public static void main(String[] args) {

		// 创建多个线程
		for (int i = 0; i < 10; i++) {
			// TODO(Tips): 这里使用10个线程来获取单例实例、如果出现多个实例新实例会将旧实例覆盖、从而导致旧实例失去引用、将会被回收[不必要的实例]。
			new Thread(() -> flag &= T081549.singleton == Singleton.getInstance()).start();
		}

		// 丢弃引用[无效]
		Singleton.singleton = null;

		System.gc();

		// 等待垃圾回收器回收实例[不一定每次都有效]
		while (true);

	}

	static class Singleton {

		private static Singleton singleton;

		private Singleton() {}

		public static Singleton getInstance() {
			if (null == singleton) {
				singleton = new Singleton();
			}
			return singleton;
		}

		@Override
		protected void finalize() throws Throwable {
			super.finalize();
			log.info("Singleton 实例: {} 已回收", this);
		}
	}
}
