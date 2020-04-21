package cn.iwk.learn.spring.annotation.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @Author iwk
 * @date 2020/4/6 4:45 下午
 * @since 1.0.0
 **/
@Slf4j
@Primary
@Component
public class Lifecycle implements
BeanNameAware, BeanFactoryAware, ApplicationContextAware, InitializingBean, DisposableBean, BeanPostProcessor {

	@Override
	public void setBeanName(String name) {
		log.debug("Lifecycle --> beanName: {}", name);
	}

	@Override
	public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
		log.debug("Lifecycle --> {}", beanFactory);
	}

	@Override
	public void destroy() throws Exception {
		log.debug("Lifecycle --> 销毁");
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		log.debug("Lifecycle --> afterPropertiesSet");
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		log.debug("Lifecycle --> {}", applicationContext);
	}

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		log.debug("Lifecycle --> {}, {}", bean, beanName);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		log.debug("Lifecycle --> {}, {}", bean, beanName);
		return bean;
	}

}
