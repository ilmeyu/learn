package cn.ilmeyu.learn.spring.bean;

import cn.ilmeyu.learn.spring.bean.beans.Phone;
import cn.ilmeyu.learn.spring.bean.beans.User;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractSingleBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

/**
 * @Author 余文楷
 * @date 2020/4/10 10:25 下午
 * @since 1.0.0
 **/
public class PhoneBeanDefinitionParser extends AbstractSingleBeanDefinitionParser {
	@Override
	protected Class<?> getBeanClass(Element element) {
		return Phone.class;
	}

	@Override
	protected void doParse(Element element, ParserContext parserContext, BeanDefinitionBuilder builder) {
		String color = element.getAttribute("color");
		String size = element.getAttribute("size");
		String remark = element.getAttribute("remark");

		if (StringUtils.hasText(color)) {
			builder.addPropertyValue("color", color);
		}
		if (StringUtils.hasText(size)) {
			builder.addPropertyValue("size", size);
		}
		if (StringUtils.hasText(remark)) {
			builder.addPropertyValue("remark", remark);
		}

	}
}
