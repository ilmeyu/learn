package com.ilme.t19.t1912;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 消费者生产者
 *
 * @author ilme
 * @date 2019/12/10 8:42 下午
 **/
@Slf4j
public class T121551 {

	public static void main(String[] args) {
		Clerk clerk = new Clerk();

		Producers producers = new Producers(clerk);
		Consumers consumers = new Consumers(clerk);
		new Thread(producers, "李四").start();
		new Thread(consumers, "张四").start();
		new Thread(consumers, "张五").start();
		new Thread(consumers, "张六").start();
	}

	static class Clerk {
		private int capacity = 0;

		Lock lock = new ReentrantLock();

		Condition condition = lock.newCondition();

		void sale() {
			lock.lock();
			while (capacity <= 0) {
				log.warn("库存不足！");
				try {
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
			log.info("{}成功卖出当前剩余{}", Thread.currentThread().getName(), --capacity);
			condition.signalAll();
		}

		void production() {
			lock.lock();
			while (capacity >= 1) {
				log.warn("库存已满！");
				try {
					condition.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
			log.info("{}成功生产当前库存{}", Thread.currentThread().getName(), ++capacity);
			condition.signalAll();
		}

	}

	static class Producers implements Runnable {
		Clerk clerk;

		Producers (Clerk clerk) {
			this.clerk = clerk;
		}

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				clerk.production();
			}
		}
	}

	static class Consumers implements Runnable {
		Clerk clerk;

		Consumers (Clerk clerk) {
			this.clerk = clerk;
		}

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				clerk.sale();
			}
		}
	}

}
