package com.ilme.t19.t1912;

import lombok.extern.slf4j.Slf4j;

/**
 * 内存可见性
 *
 * @author yuwenkai
 * @date 2019/12/4 7:42 下午
 **/
@Slf4j
public class T041942 {

	public static void main(String[] args) throws Exception {

		T t = new T();

		Thread thread = new Thread(t);

		thread.start();

		while (true) {
			if (!t.flag) {
				log.info("flag: {}", t.flag);
				break;
			}
		}

	}

	static class T implements Runnable {

		volatile boolean flag = true;

		@Override
		public void run() {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			flag = false;
		}
	}

}
