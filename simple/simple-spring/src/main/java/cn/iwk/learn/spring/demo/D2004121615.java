package cn.iwk.learn.spring.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.EmbeddedValueResolverAware;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringValueResolver;

/**
 * Spring Aware 演示
 *
 * @Author iwk
 * @date 2020/4/12 4:15 下午
 * @since 1.0.0
 **/
@Slf4j
public class D2004121615 {

    public static void main( String[] args ) {
        ApplicationContext context = new AnnotationConfigApplicationContext( Config.class );
        log.debug( "bean: {}", context.getBean( DemoBean.class ) );
    }

    @Configuration
    static class Config {
        @Bean
        DemoBean demoBean( ) {
            return new DemoBean();
        }
    }

    static class DemoBean implements ApplicationContextAware, BeanNameAware, EmbeddedValueResolverAware {

        @Override
        public void setApplicationContext( ApplicationContext applicationContext ) throws BeansException {
            log.debug( "ApplicationContextAware: {}", applicationContext );
        }

        @Override
        public void setBeanName( String name ) {
            log.debug( "BeanNameAware: {}", name );
        }

        @Override
        public void setEmbeddedValueResolver( StringValueResolver resolver ) {
            log.debug( "EmbeddedValueResolverAware: {}", resolver );
            log.debug( "EmbeddedValueResolverAware.resolver: {}", resolver.resolveStringValue( "os.name" ) );
        }
    }

}
