package cn.iwk.learn.spring.bean;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

/**
 * @Author iwk
 * @date 2020/4/10 10:33 下午
 * @since 1.0.0
 **/
public class CustomNamespaceHandler extends NamespaceHandlerSupport {
	@Override
	public void init() {
		registerBeanDefinitionParser("user", new UserBeanDefinitionParser());

		registerBeanDefinitionParser("phone", new PhoneBeanDefinitionParser());
	}
}
