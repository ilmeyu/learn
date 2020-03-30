package com.ilme.t20.t2001;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

/**
 * 单例模式 - 饿汉式
 * 内存浪费演示
 *
 * @author ilme
 * @date 2020/1/8 2:57 下午
 **/
@Test
@Slf4j
public class T081457 {

	public static void main(String[] args) {

//		Singleton instance_1 = Singleton.getInstance();
//		Singleton instance_2 = Singleton.getInstance();
//		log.info("instance_1 == instance_2: {}", instance_1 == instance_2);

		// 加载类[内部的静态代码块会执行]
		Class<Singleton> clazz = Singleton.class;

		// TODO(Tips): 这里我们并没有使用实例、仅仅只是加载了这个类、就已经加载了这个类、从而导致浪费
		// 输出实例
		log.info("Singleton实例: {}", Singleton.singleton);

		// 丢弃引用
		Singleton.singleton = null;

		System.gc();

		// 等待垃圾回收器回收实例[不一定每次都有效]
		while (true);
	}

	static class Singleton {

		private static Singleton singleton;

		static {
			singleton = new Singleton();
		}

		private Singleton() {}

		public static Singleton getInstance() {
			return singleton;
		}

		@Override
		protected void finalize() throws Throwable {
			super.finalize();
			log.info("Singleton 实例: {} 已回收", this);
		}
	}
}
