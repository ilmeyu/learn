package cn.ilmeyu.learn.spring.annotation.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Author 余文楷
 * @date 2020/4/4 9:51 下午
 * @since 1.0.0
 **/
@Data
@AllArgsConstructor
public class Person {

	String name;

	Integer age;

}
