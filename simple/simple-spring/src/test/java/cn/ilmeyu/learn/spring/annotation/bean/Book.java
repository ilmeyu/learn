package cn.iwk.learn.spring.annotation.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * @Author iwk
 * @date 2020/4/4 10:23 下午
 * @since 1.0.0
 **/
@Data
@Slf4j
@Component
@NoArgsConstructor
@AllArgsConstructor
public class Book implements InitializingBean, DisposableBean {

	String name;

	Double price;

	// 属性设置完成时候调用
	@Override
	public void afterPropertiesSet() {
		log.debug("{}bean初始化完成...", this);
	}

	// 销毁时调用
	@Override
	public void destroy() {
		log.debug("{}bean销毁...", this);
	}
}
