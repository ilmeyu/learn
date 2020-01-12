package com.ilme.t20.t2001;

import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.ListeningExecutorService;
import com.google.common.util.concurrent.MoreExecutors;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 单例模式 - 懒汉式
 * 同步、双重判定
 *
 * @author ilme
 * @date 2020/1/8 4:28 下午
 **/
@Slf4j
public class T081628 {

	public static void main(String[] args) throws Exception {

		List<ListenableFuture<Singleton>> futureList = new ArrayList<>();

		ExecutorService pool = Executors.newFixedThreadPool(10);

		ListeningExecutorService service = MoreExecutors.listeningDecorator(pool);

		// 创建多个线程
		for (int i = 0; i < 10; i++) {
			// TODO(Tips): 这里使用10个线程来获取单例实例、如果出现多个实例新实例会将旧实例覆盖、从而导致旧实例失去引用、将会被回收[不必要的实例]。
			futureList.add(service.submit(Singleton::getInstance));
		}

		ListenableFuture<List<Singleton>> future = Futures.allAsList(futureList);

		List<Singleton> singletons = future.get();

		// 丢弃引用
		Singleton.singleton = null;

		System.gc();

		// 等待垃圾回收器回收实例[不一定每次都有效]
		while (true);
	}

	static class Singleton {
		private static volatile Singleton singleton;

		private Singleton() {}

		public static Singleton getInstance() {

			if (null == singleton) {
				synchronized (Singleton.class) {
					if (null == singleton) {
						singleton = new Singleton();
					}
				}
			}

			return singleton;
		}
	}

}
