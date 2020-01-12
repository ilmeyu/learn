package com.ilme.t19.t1912;

import lombok.extern.slf4j.Slf4j;

/**
 * 消费者生产者 - 虚假唤醒
 *
 * @author ilme
 * @author ilme
 * @date 2019/12/10 8:42 下午
 **/
@Slf4j
public class T102041 {

	public static void main(String[] args) {
		Clerk clerk = new Clerk();

		Consumers consumers = new Consumers(clerk);
		Producers producers = new Producers(clerk);

		new Thread(consumers, "张三").start();
		new Thread(producers, "李四").start();
		new Thread(consumers, "张六").start();
	}

	static class Clerk {
		private int capacity = 0;

		synchronized void sale() {
			while (capacity <= 0) {
				log.warn("库存不足！");
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			log.info("{}成功卖出当前剩余{}", Thread.currentThread().getName(), --capacity);
			this.notifyAll();
		}

		synchronized void production() {
			while (capacity >= 1) {
				log.warn("库存已满！");
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			log.info("{}成功生产当前库存{}", Thread.currentThread().getName(), ++capacity);
			this.notifyAll();
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
					Thread.sleep(100);
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
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				clerk.sale();
			}
		}
	}

}
