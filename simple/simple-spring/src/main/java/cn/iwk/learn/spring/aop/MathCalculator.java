package cn.iwk.learn.spring.aop;

import lombok.extern.slf4j.Slf4j;

/**
 * @Author iwk
 * @date 2020/4/19 3:32 下午
 * @since 1.0.0
 **/
@Slf4j
public class MathCalculator {

    public Integer calculate(int x, int y) {
        log.debug("计算: {} ✖ {}", x, y);
        return x * y;
    }

}
