package com.ilme.t20.t2002;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * 单文件 springboot
 *
 * @author ilme
 * @date 2020/2/20 9:08 下午
 **/
@Slf4j
@SpringBootApplication
public class T202108 {

	public static void main(String[] args) {
		SpringApplication.run(T202108.class, args);
	}

	@RestController(value = "/")
	static class WebController {

		@GetMapping(value = "/index")
		void index(HttpServletRequest request) {
			log.info("IP: {}", request.getRemoteHost());
		}
	}

}
