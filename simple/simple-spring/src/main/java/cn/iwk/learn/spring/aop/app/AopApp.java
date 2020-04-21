package cn.iwk.learn.spring.aop.app;

import cn.iwk.learn.spring.aop.MathCalculator;
import cn.iwk.learn.spring.aop.config.MainConfig;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * @Author iwk
 * @date 2020/4/19 3:38 下午
 * @since 1.0.0
 **/
public class AopApp {

    public static void main( String[] args ) {
        ApplicationContext ctx = new AnnotationConfigApplicationContext( MainConfig.class );

        MathCalculator calculator = ctx.getBean( MathCalculator.class );

        Integer result = calculator.calculate( 9, 9 );

    }

}
