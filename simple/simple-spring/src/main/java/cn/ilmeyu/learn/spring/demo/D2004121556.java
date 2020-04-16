package cn.ilmeyu.learn.spring.demo;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import javax.inject.Inject;

/**
 * Spring 花式注入属性
 *
 * @Author 余文楷
 * @date 2020/4/12 3:56 下午
 * @since 1.0.0
 **/
@Slf4j
public class D2004121556 {

    public static void main( String[] args ) {
        ApplicationContext context = new AnnotationConfigApplicationContext( Config.class );

        for ( String beanName : context.getBeanDefinitionNames() ) {
            log.debug("bean: {}", context.getBean( beanName ) );
        }
    }

    @Configuration
    static class Config {
        @Bean
        Book book() {
            return new Book();
        }

        @Bean
        Phone phone() {
            return new Phone();
        }

        @Bean
        Computer computer() {
            return new Computer();
        }

        @Bean
        Cup cup( Color color ) {
            return new Cup( color );
        }

        @Bean
        Color color() {
            return new Color();
        }
    }

    @ToString
    static class Book {
        @Inject
        Color color;
    }

    @ToString
    static class Phone {
        @Resource
        Color color;
    }

    @ToString
    @SuppressWarnings( "all" )
    static class Computer {
        @Autowired
        Color color;
    }

    @ToString
    static class Cup {
        Color color;

        public Cup( Color color ) {
            this.color = color;
        }
    }

    static class Color {
        String name;
    }

}
