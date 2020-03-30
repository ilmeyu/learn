package com.ilme.t20.t2003;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

/**
 * jdk8 Instant 获取时间戳
 *
 * @author ilme
 * @date 2020/3/25 9:55 上午
 **/
@Slf4j
public class T250955 {

	public static void main(String[] args) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

		String date = dateFormat.format(new Date());
		log.info("当前日期: {}", date);

		Date d1 = dateFormat.parse(date);
		Instant d2 = Instant.now();

//		d1.toInstant().
//		LocalDate now = LocalDate.now();
//
//		log.info("new Date(): {}, Instant: {}", d1.getTime(), now);
	}

}
