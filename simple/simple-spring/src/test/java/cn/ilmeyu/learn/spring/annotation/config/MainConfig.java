package cn.iwk.learn.spring.annotation.config;

import cn.iwk.learn.spring.annotation.bean.Famous;
import cn.iwk.learn.spring.annotation.bean.Person;
import cn.iwk.learn.spring.annotation.condition.MacOsCondition;
import cn.iwk.learn.spring.annotation.service.WebService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @Author iwk
 * @date 2020/4/4 9:54 下午
 * @since 1.0.0
 **/
// 配置类 == 配置文件
@Configuration // 标记: 这是一个spring配置文件
@ComponentScan(
	value = {  // 包扫描路径
		"cn.iwk.learn.spring.annotation"
	},
	includeFilters = { // 指定扫描的时候包含哪些组件
		@ComponentScan.Filter(type = FilterType.ANNOTATION, value = {Controller.class, Component.class})
	},
	useDefaultFilters = false
	// excludeFilters = Filter[] 指定扫描的时候按照什么规则排除哪些组件
)
@Import(value = {WebService.class})
@Slf4j
public class MainConfig {

	@Primary
	@Bean(value = "person", initMethod = "init", destroyMethod = "destroy") // 向容器注册一个bean, 类型为返回值类型, id默认为方法名
	@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	Person person() {
		log.debug("loading person");
		return new Person("张三", 20);
	}

	@Bean
	@Lazy
	@Conditional(value = {MacOsCondition.class})
	Famous steve() {
		return new Famous("乔布斯");
	}

}
