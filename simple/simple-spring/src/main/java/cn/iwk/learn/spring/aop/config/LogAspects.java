package cn.iwk.learn.spring.aop.config;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Author iwk
 * @date 2020/4/19 3:59 下午
 * @since 1.0.0
 **/
@Slf4j
@Aspect
public class LogAspects {

    @Pointcut(value = "execution( public Integer cn.iwk.learn.spring.aop.MathCalculator.*(..) )")
    void pointCut( ) {
    }

    @Before(value = "pointCut()")
    void logStart( JoinPoint joinPoint ) {
        log.debug( "前置通知 => 参数为: {}", Arrays.toString( joinPoint.getArgs() ) );
    }

    @After(value = "pointCut()")
    void logEnd( JoinPoint joinPoint ) {
        log.debug( "后置通知 => " );
    }

    @AfterReturning(value = "pointCut()", returning = "result")
    void logReturn( Object result ) {
        log.debug( "返回通知 => 返回值: {}", result );
    }

    @AfterThrowing(value = "pointCut()", throwing = "throwable")
    void logException( JoinPoint joinPoint, Throwable throwable ) {
        log.debug( "异常通知 => 异常信息: {}", throwable.getMessage() );
    }

    @Around(value = "pointCut()")
    Object logException( ProceedingJoinPoint pjp ) throws Throwable {
        Object[] args = pjp.getArgs();

        log.debug( "环绕通知 => 参数值: {}", Arrays.toString( args ) );

        MethodSignature signature  = ( MethodSignature ) pjp.getSignature();

        Method method = signature.getMethod();

        String methodName = method.getName();

        log.debug( "环绕通知 => 方法名: {}", methodName );

        Object proceed = pjp.proceed();

        log.debug( "环绕通知 => 返回值: {}", proceed );

        return proceed;
    }

}
