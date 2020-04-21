package cn.iwk.learn.spring.aop.config;

import cn.iwk.learn.spring.aop.MathCalculator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @Author iwk
 * @date 2020/4/19 4:51 下午
 * @since 1.0.0
 **/
@EnableAspectJAutoProxy
@Configuration
public class MainConfig {

    @Bean
    MathCalculator mathCalculator( ) {
        return new MathCalculator();
    }

    @Bean
    LogAspects logAspects( ) {
        return new LogAspects();
    }

}
