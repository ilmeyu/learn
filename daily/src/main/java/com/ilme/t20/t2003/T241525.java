package com.ilme.t20.t2003;

import lombok.extern.slf4j.Slf4j;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 日期比对
 *
 * @author ilme
 * @date 2020/3/24 3:25 下午
 **/
@Slf4j
public class T241525 {

	public static void main(String[] args) throws Exception {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date timeLow = simpleDateFormat.parse("2020-01-03");
		Date timeHigh = simpleDateFormat.parse("2020-01-02");

		boolean bool = timeLow == null || timeHigh == null || timeLow.getTime() <= timeHigh.getTime();

		log.info("bool: {}", bool);
	}

}
