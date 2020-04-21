package cn.iwk.learn.spring.annotation.condition;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Optional;

/**
 * @Author iwk
 * @date 2020/4/5 11:05 上午
 * @since 1.0.0
 **/
public class MacOsCondition implements Condition {

	@Override
	public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
		// 是否是Mac os系统
		// 获取到ioc使用的beanFactory
		BeanFactory beanFactory = context.getBeanFactory();
		// 获取类加载器
		ClassLoader classLoader = context.getClassLoader();
		// 获取当前环境信息
		Environment environment = context.getEnvironment();
		// 获取bean定义的注册类
		BeanDefinitionRegistry registry = context.getRegistry();

		return Optional.ofNullable(environment.getProperty("os.name")).orElse("").contains("Mac");
	}

}
