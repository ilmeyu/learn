package cn.iwk.learn.spring.bean;

import cn.iwk.learn.spring.bean.beans.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @Author iwk
 * @date 2020/4/10 10:25 下午
 * @since 1.0.0
 **/
public class UserBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
	@Override
	protected Class<?> getBeanClass(Element element) {
		return User.class;
	}

	@Override
	protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
		String id = element.getAttribute("id");
		String userName = element.getAttribute("userName");
		String email = element.getAttribute("email");

		if (StringUtils.hasText(id)) {
			builder.addPropertyValue("id", id);
		}
		if (StringUtils.hasText(userName)) {
			builder.addPropertyValue("userName", userName);
		}
		if (StringUtils.hasText(email)) {
			builder.addPropertyValue("email", email);
		}

	}
}
