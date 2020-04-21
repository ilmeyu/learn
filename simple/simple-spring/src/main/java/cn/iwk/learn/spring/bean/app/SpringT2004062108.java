package cn.iwk.learn.spring.bean.app;

import cn.iwk.learn.spring.bean.beans.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * bean的创建
 *
 * @Author iwk
 * @date 2020/4/6 9:07 下午
 * @since 1.0.0
 **/
@Slf4j
public class SpringT2004062108 {

	public static void main(String[] args) {
		BeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("bean/SpringT2004062108.xml"));

		Book book = beanFactory.getBean(Book.class);

		log.debug("{}", book);
	}

}
