package cn.iwk.learn.spring.demo;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Spring 注解导入外部配置文件并使用 @Value 注入
 *
 * @Author iwk
 * @date 2020/4/12 12:10 下午
 * @since 1.0.0
 **/
@Slf4j
public class D2004121210 {

    public static void main( String[] args ) {
        BeanFactory context = new AnnotationConfigApplicationContext( Config.class );

        log.debug( "{}", context.getBean( Person.class ) );
    }

    @PropertySource(value = {"classpath:demo/D2004121210.properties"})
    @Configuration
    static class Config {
        @Bean
        Person person( ) {
            return new Person();
        }
    }

    @Data
    static class Person {
        @Value("张三")
        String name;
        @Value("#{21}")
        Integer age;
        @Value("${person.sex}")
        String sex;
    }
}

