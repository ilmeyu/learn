package com.ilme.t19.t1912;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 线程池
 *
 * @author ilme
 * @date 2019/12/13 4:09 下午
 **/
@Slf4j
public class T131609 {

	public static void main(String[] args) {
		ExecutorService executor = Executors.newFixedThreadPool(5);

		Runnable run = () -> {
			int sum = 0;
			for (int i = 0; i < 1000; i++) {
				sum += i;
			}
			log.info("sum: {}", sum);
		};

		for (int i = 0; i < 5; i++) {
			executor.submit(run);
		}

		executor.shutdown();
	}
}
