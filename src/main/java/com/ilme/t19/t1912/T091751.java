package com.ilme.t19.t1912;

import lombok.extern.slf4j.Slf4j;

import java.time.Duration;
import java.time.Instant;
import java.util.concurrent.CountDownLatch;

/**
 * 闭锁
 *
 * @author yuwenkai
 * @date 2019/12/9 5:51 下午
 **/
@Slf4j
public class T091751 {

	public static void main(String[] args) {
		final int count = 5;
		CountDownLatch countDownLatch = new CountDownLatch(count);

		T t = new T(countDownLatch);

		Instant begin = Instant.now();

		for (int i = 0; i < count; i++) {
			new Thread(t).start();
		}

		try {
			countDownLatch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		Instant end = Instant.now();

		log.info("花费时间: {}ms", Duration.between(begin, end).toMillis());
	}

	static class T implements Runnable {

		CountDownLatch downLatch;

		T (CountDownLatch countDownLatch) {
			this.downLatch = countDownLatch;
		}

		@Override
		public void run() {
			int sum = 0;

			for (int i = 0; i < 2000000000; i++) {
				sum += i;
			}

			log.info("sum: {}", sum);

			downLatch.countDown();
		}
	}

}
