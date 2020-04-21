package cn.iwk.learn.spring.annotation.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;

/**
 * @Author iwk
 * @date 2020/4/4 10:44 下午
 * @since 1.0.0
 **/
@Slf4j
@Controller
public class WebController {

	public void sayHello() {
		log.debug("{}", "hello");
	}

}
