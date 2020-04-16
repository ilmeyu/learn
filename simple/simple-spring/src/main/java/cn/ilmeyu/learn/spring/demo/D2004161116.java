package cn.ilmeyu.learn.spring.demo;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

import javax.inject.Inject;

/**
 * 循环依赖
 *
 * @Author ilmeyu
 * @date 2020/4/16 11:16 上午
 * @since 1.0.0
 **/
@Slf4j
public class D2004161116 {

    public static void main( String[] args ) {
        DefaultListableBeanFactory beanFactory = new XmlBeanFactory( new ClassPathResource( "demo/D2004161116_application.xml" ) );

        for ( String beanDefinitionName : beanFactory.getBeanDefinitionNames() ) {
            log.debug( "bean: {}", beanFactory.getBean( beanDefinitionName ) );
        }

    }

    @ToString
    static class A {
        @Inject
        B b;
    }

    @ToString
    static class B {
        @Inject
        C c;
    }

    @ToString
    static class C {
        @Inject
        A a;
    }

}
