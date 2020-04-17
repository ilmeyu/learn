package cn.ilmeyu.learn.spring.beans;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @Author ilmeyu
 * @date 2020/4/17 11:02 下午
 * @since 1.0.0
 **/
public class App {

    public static void main( String[] args ) {
        BeanFactory beanFactory = new XmlBeanFactory( new ClassPathResource( "application.xml" ) );

        User bean = beanFactory.getBean( User.class );

        System.out.println( bean );
    }

}
