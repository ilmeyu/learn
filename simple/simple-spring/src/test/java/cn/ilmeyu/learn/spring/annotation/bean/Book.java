package cn.ilmeyu.learn.spring.annotation.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

/**
 * @Author 余文楷
 * @date 2020/4/4 10:23 下午
 * @since 1.0.0
 **/
@Data
@Component
@NoArgsConstructor
@AllArgsConstructor
public class Book {

	String name;

	Double price;

}
