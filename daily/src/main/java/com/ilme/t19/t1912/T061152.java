package com.ilme.t19.t1912;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 多线程 阻塞式队列
 *
 * @author yuwenkai
 * @date 2019/12/6 11:52 上午
 **/
@Slf4j
public class T061152 {

	public static void main(String[] args) {
		BlockingQueue blockingQueue = new LinkedBlockingQueue<Integer>();

		for (int i = 0; i < 10; i++) {
			new Thread(new T(blockingQueue)).start();
		}

		log.info("blockingQueue: {}", blockingQueue);

	}

	static class T implements Runnable {

		BlockingQueue<Integer> queue;

		T (BlockingQueue queue) {
			this.queue = queue;
		}

		@Override
		public void run() {
			int val = (int) (Math.random() * 100);
			queue.add(val);

			log.info("队列新增: {}", val);
		}
	}

}
