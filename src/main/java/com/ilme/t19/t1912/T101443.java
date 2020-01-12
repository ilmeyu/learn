package com.ilme.t19.t1912;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Lock用法
 *
 * @author yuwenkai
 * @date 2019/12/10 2:43 下午
 **/
@Slf4j
public class T101443 {

	public static void main(String[] args) {

		Ticket ticket = new Ticket();

		new Thread(new T(ticket), "张三").start();
		new Thread(new T(ticket), "张四").start();
		new Thread(new T(ticket), "张五").start();
	}

	static class Ticket {
		int ticket = 100;
		Lock lock = new ReentrantLock();

		int sold() {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

			lock.lock();
			try {
				--ticket;
			} finally {
				lock.unlock();
			}
//			synchronized (this) {
//				--ticket;
//			}
			return ticket;
		}
	}

	static class T implements Runnable {

		Ticket ticket;

		T (Ticket ticket) {
			this.ticket = ticket;
		}

		@Override
		public void run() {
			while (true) {
				int sold = ticket.sold();
				if (sold > 0) {
					log.info("{}卖出、当前剩余: {}", Thread.currentThread().getName(), sold);
				} else {
					return;
				}
			}
		}
	}
}
