package com.ilme.t20.t2004;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * spring 注解开发
 *
 * @Author iwk
 * @date 2020/4/4 9:06 下午
 * @since 1.0.0
 **/
@Slf4j
public class T042106 {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(Config.class);

		Person person = context.getBean(Person.class);

		log.debug("{}", person);
	}

	@Configuration
	static class Config {

		@Bean
		public Person person() {
			return new Person( "张三", 10 );
		}

	}

	@Data
	@AllArgsConstructor
	static class Person {

		String name;

		Integer age;

	}

	@Data
	@AllArgsConstructor
	static class Book {

		String name;

		Integer price;

	}

}
