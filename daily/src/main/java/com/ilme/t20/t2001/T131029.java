package com.ilme.t20.t2001;

import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

/**
 * 桥接模式
 *
 * @author ilme
 * @date 2020/1/13 10:29 上午
 **/
@Test
@Slf4j
public class T131029 {

	public void test() {
		Phone apple = new Apple(new Memory_4G());
		apple.info();

		Phone pears = new Pears(new Memory_8G());
		pears.info();
	}

	interface Memory {
		void info();
	}

	static class Memory_4G implements Memory {
		@Override
		public void info() {
			log.info("4G");
		}
	}

	static class Memory_8G implements Memory {
		@Override
		public void info() {
			log.info("8G");
		}
	}

	static abstract class Phone {
		Memory memory;

		protected Phone(Memory memory) {
			this.memory = memory;
		}

		abstract void info();
	}

	static class Apple extends Phone {

		public Apple(Memory memory) {
			super(memory);
		}

		@Override
		void info() {
			log.info("手机品牌: 苹果手机");
			memory.info();
		}
	}

	static class Pears extends Phone {

		public Pears(Memory memory) {
			super(memory);
		}

		@Override
		void info() {
			log.info("手机品牌: 梨子手机");
			memory.info();
		}
	}
}
