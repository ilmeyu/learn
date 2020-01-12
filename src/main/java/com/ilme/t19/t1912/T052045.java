package com.ilme.t19.t1912;

import lombok.extern.slf4j.Slf4j;

/**
 * 生产者消费者
 *
 * @author yuwenkai
 * @date 2019/12/5 8:45 下午
 **/
@Slf4j
public class T052045 {

	public static void main(String[] args) {
		Clerk clerk = new Clerk();

		new Thread(new Producer(clerk)).start();
		new Thread(new Producer(clerk)).start();
		new Thread(new Consumer(clerk)).start();

	}

	static class Clerk {
		int goods;

		synchronized void procurement() {
			if (goods >= 10) {
				log.warn("仓库已满！");
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return;
			}
			log.info("{}进货, 当前库存：{}", Thread.currentThread().getName(), ++goods);
			this.notifyAll();
		}

		synchronized void sold() {
			if (goods <= 0) {
				log.warn("当前缺货！");
				try {
					this.wait();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return;
			}
			log.info("{}卖出, 当前库存：{}", Thread.currentThread().getName(), --goods);
			this.notifyAll();
		}
	}

	static class Producer implements Runnable {
		Clerk clerk;

		Producer(Clerk clerk) {
			this.clerk = clerk;
		}

		@Override
		public void run() {
			while (true) {
				try {
					Thread.sleep(102);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				clerk.procurement();
			}
		}
	}

	static class Consumer implements Runnable {
		Clerk clerk;

		Consumer(Clerk clerk) {
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
				clerk.sold();
			}
		}
	}
}
