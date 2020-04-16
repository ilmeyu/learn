package cn.ilmeyu.learn.spring.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * Spring populate
 *
 * @Author 余文楷
 * @date 2020/4/13 5:06 下午
 * @since 1.0.0
 **/
@Slf4j
public class D2004131706 {

    public static void main( String[] args ) {
        BeanFactory beanFactory = new XmlBeanFactory( new ClassPathResource( "demo/D2004131706_application.xml" ) );

        log.debug( "person: {}", beanFactory.getBean( Person.class ) );
    }

    @Data
    @ToString
    static class Person {
        String name;
        Book book;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class Book {
        String title;
        Double price;
    }

}
