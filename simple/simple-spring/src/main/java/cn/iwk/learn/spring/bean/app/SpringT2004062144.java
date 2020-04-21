package cn.iwk.learn.spring.bean.app;

import cn.iwk.learn.spring.bean.beans.IgnoreInterface;
import cn.iwk.learn.spring.bean.beans.Phone;
import cn.iwk.learn.spring.bean.beans.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @Author iwk
 * @date 2020/4/6 9:44 下午
 * @since 1.0.0
 **/
@Slf4j
public class SpringT2004062144 {

	public static void main(String[] args) {
		DefaultListableBeanFactory beanFactory = new XmlBeanFactory(new ClassPathResource("bean/SpringT2004062144.xml"));

//		beanFactory.ignoreDependencyInterface(IgnoreInterface.class);

		IgnoreInterface bean = beanFactory.getBean(IgnoreInterface.class);

		log.debug("{}", bean);

		User user = beanFactory.getBean(User.class);
		Phone phone = beanFactory.getBean(Phone.class);

		log.debug("{}\t{}", user, phone);
	}

}
