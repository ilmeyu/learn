package cn.iwk.learn.spring.annotation.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * 后置处理器
 *
 * @Author iwk
 * @date 2020/4/5 5:18 下午
 * @since 1.0.0
 **/
@Slf4j
@Component
public class CustomBeanPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
		log.debug("bean: {}后置处理器之前.....", bean);
		return bean;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
		log.debug("bean: {}后置处理器之后.....", bean);
		return bean;
	}

}
