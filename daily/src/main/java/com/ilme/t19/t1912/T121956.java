package com.ilme.t19.t1912;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 线程顺序控制
 *
 * @author ilme
 * @date 2019/12/12 7:56 下午
 **/
@Slf4j
public class T121956 {

	public static void main(String[] args) {
		T t = new T();

		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				t.loopA();
			}, "A").start();

			new Thread(() -> {
				t.loopB();
			}, "B").start();

			new Thread(() -> {
				t.loopC();
			}, "C").start();
		}
	}

	static class T {
		int flag = 0;

		Lock lock = new ReentrantLock();
		Condition conditionA = lock.newCondition();
		Condition conditionB = lock.newCondition();
		Condition conditionC = lock.newCondition();

		public void loopA() {
			try {
				lock.lock();
				if (0 != flag) {
					conditionA.await();
				}
				Thread.sleep(1000);
				log.info("{}", Thread.currentThread().getName());
				flag = 1;
				conditionA.signal();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}

		public void loopB() {
			try {
				lock.lock();
				if (1 != flag) {
					conditionB.await();
				}
				Thread.sleep(1000);
				log.info("{}", Thread.currentThread().getName());
				flag = 2;
				conditionB.signal();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}

		public void loopC() {
			try {
				lock.lock();
				if (2 != flag) {
					conditionC.await();
				}
				Thread.sleep(1000);
				log.info("{}", Thread.currentThread().getName());
				flag = 0;
				conditionC.signal();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				lock.unlock();
			}
		}
	}
}
