package com.ilme.t19.t1912;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * callable
 *
 * @author yuwenkai
 * @date 2019/12/10 11:39 上午
 **/
@Slf4j
public class T101139 {

	public static void main(String[] args) {
		T t = new T();

		FutureTask<Long> futureTask = new FutureTask(t);

		new Thread(futureTask).start();

		try {
			log.info("线程开始执行");

			Long result = futureTask.get();

			log.info("result: {}", result);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}

		log.info("end...");

	}

	static class T implements Callable<Long> {

		@Override
		public Long call() throws Exception {
			long sum = 0;

			for (int i = 0; i < Integer.MAX_VALUE; i++) {
				sum *= i;
			}
			return sum;
		}
	}
}
