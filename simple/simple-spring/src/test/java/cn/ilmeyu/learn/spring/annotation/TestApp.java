package cn.ilmeyu.learn.spring.annotation;

import cn.ilmeyu.learn.spring.annotation.bean.Person;
import cn.ilmeyu.learn.spring.annotation.config.MainConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.Test;

/**
 * @Author 余文楷
 * @date 2020/4/4 9:54 下午
 * @since 1.0.0
 **/
@Test
@Slf4j
public class TestApp {

	public void demo1() {
		ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
		Person person = context.getBean(Person.class);

		log.debug("{}", person);
	}

	public void demo2() {
		ApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
		String[] beanNames = context.getBeanDefinitionNames();

		log.debug("==== beans =====");
		for (String beanName : beanNames) {
			log.debug("{}", beanName);
		}
	}

}
