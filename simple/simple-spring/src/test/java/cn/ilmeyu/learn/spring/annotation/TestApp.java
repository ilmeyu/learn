package cn.iwk.learn.spring.annotation;

import cn.iwk.learn.spring.annotation.bean.Person;
import cn.iwk.learn.spring.annotation.config.MainConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.Test;

/**
 * @Author iwk
 * @date 2020/4/4 9:54 下午
 * @since 1.0.0
 **/
@Test
@Slf4j
public class TestApp {

	/**
	 * bean扫描策略
	 */
	public void testBeanScan() {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MainConfig.class);
		Person person = context.getBean(Person.class);

		log.debug("{}", person);
		log.debug("{}\t{}", context.getBean(Person.class), context.getBean(Person.class));

		String[] beanNames = context.getBeanDefinitionNames();

		log.debug("==== beans =====");
		for (String beanName : beanNames) {
			log.debug("{}", beanName);
		}

		context.close();
	}

}
