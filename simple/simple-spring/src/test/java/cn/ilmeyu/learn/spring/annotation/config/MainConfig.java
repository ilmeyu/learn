package cn.ilmeyu.learn.spring.annotation.config;

import cn.ilmeyu.learn.spring.annotation.bean.Person;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

/**
 * @Author 余文楷
 * @date 2020/4/4 9:54 下午
 * @since 1.0.0
 **/
// 配置类 == 配置文件
@Configuration // 标记: 这是一个spring配置文件
@ComponentScan(
	value = {  // 包扫描路径
		"cn.ilmeyu.learn.spring.annotation"
	},
	includeFilters = { // 指定扫描的时候包含哪些组件
		@ComponentScan.Filter(type = FilterType.ASPECTJ, pattern = "execution(* cn.ilmeyu.learn.spring.annotation...(..))")
	},
	useDefaultFilters = false
	// excludeFilters = Filter[] 指定扫描的时候按照什么规则排除哪些组件
)
public class MainConfig {

	// 向容器注册一个bean, 类型为返回值类型, id默认为方法名
	@Bean(value = "person")
	Person person() {
		return new Person("张三", 20);
	}
}
