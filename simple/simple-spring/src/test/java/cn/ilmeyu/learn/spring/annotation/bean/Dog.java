package cn.ilmeyu.learn.spring.annotation.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Author 余文楷
 * @date 2020/4/5 5:15 下午
 * @since 1.0.0
 **/
@Slf4j
@Component
public class Dog implements ApplicationContextAware {

	ApplicationContext applicationContext;

	@PostConstruct
	void init() {
		log.debug("{}初始化完成...", this);
	}

	@PreDestroy
	void destroy() {
		log.debug("{}已销毁...", this);
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}
}
