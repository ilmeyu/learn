package cn.iwk.learn.spring.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author iwk
 * @date 2020/4/20 11:24 上午
 * @since 1.0.0
 **/
@Slf4j
public class D2004201124 {

    public static void main( String[] args ) {
        ApplicationContext ctx;
        ctx = new ClassPathXmlApplicationContext( "classpath:demo/D2004201124_application.xml" );

        DemoBean demoBean = ctx.getBean( DemoBean.class );

        log.debug( "demoBean: {}", demoBean );
    }

    static class DemoBean {

    }

}