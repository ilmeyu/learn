package cn.iwk.learn.spring.ioc.beans;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.inject.Inject;

/**
 * @Author iwk
 * @date 2020/4/11 9:28 下午
 * @since 1.0.0
 **/
@Getter
@Setter
@ToString
public class Car {

    private int maxSpeed;

    @Inject
    private String brand;

    private double price;

}
