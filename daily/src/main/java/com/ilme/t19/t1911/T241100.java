package com.ilme.t19.t1911;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.time.Period;

/**
 * 计算日期间隔
 *
 * @author yuwenkai
 * @date 2019/11/24 11:00 上午
 **/
@Slf4j
public class T241100 {

	public static void main(String[] args) {
		LocalDate ldt = LocalDate.of(1998, 11, 14);

		LocalDate now = LocalDate.now();

		Period period = Period.between(ldt, now);

		log.info("年龄: {}年{}天", period.getYears(), period.getDays());
	}
}
