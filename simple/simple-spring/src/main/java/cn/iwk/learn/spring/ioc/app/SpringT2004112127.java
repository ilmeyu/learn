package cn.iwk.learn.spring.ioc.app;

import cn.iwk.learn.spring.ioc.beans.Car;
import cn.iwk.learn.spring.ioc.beans.CarFactoryBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;

/**
 * @Author iwk
 * @date 2020/4/11 9:26 下午
 * @since 1.0.0
 **/
@Slf4j
public class SpringT2004112127 {

    public static void main( String[] args ) {
        BeanFactory beanFactory = new XmlBeanFactory( new ClassPathResource( "ioc/SpringT2004112127.xml" ) );

        CarFactoryBean factoryBean = beanFactory.getBean( CarFactoryBean.class );
        Car car = beanFactory.getBean( Car.class );
        Object bean = beanFactory.getBean( "&car" );

        log.debug( "{}, {}", factoryBean, car );
        log.debug( "{}", bean );

    }

}
