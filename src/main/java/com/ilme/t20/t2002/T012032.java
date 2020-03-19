package com.ilme.t20.t2002;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 设计模式 - 组合模式
 *
 * @author ilme
 * @date 2020/2/1 8:32 下午
 **/
@Slf4j
public class T012032 {

	public static void main(String[] args) {
		School school = new ConcreteSchool("家里蹲大学");

		School internetDepartment = new InternetDepartment("信息工程部");
		School securityDepartment = new SecurityDepartment("信息安全部");

		school.add(internetDepartment);
		school.add(securityDepartment);

		school.show();
	}

	static interface School {
		void add(School school);

		void show();
	}

	static abstract class AbstractSchool implements School {
		String name;

		AbstractSchool(String name) {
			this.name = name;
		}

		@Override
		public void add(School school) {
			throw new UnsupportedOperationException();
		}
	}

	static class ConcreteSchool extends AbstractSchool {

		List<School> partList = new ArrayList<>();

		ConcreteSchool(String name) {
			super(name);
		}

		@Override
		public void add(School school) {
			partList.add(school);
		}

		@Override
		public void show() {
			log.info("{}", name);
			partList.forEach(e -> e.show());
		}
	}

	static class InternetDepartment extends AbstractSchool {
		InternetDepartment(String name) {
			super(name);
		}

		@Override
		public void show() {
			log.info("{}", name);
		}
	}

	static class SecurityDepartment extends AbstractSchool {
		SecurityDepartment(String name) {
			super(name);
		}

		@Override
		public void show() {
			log.info("{}", name);
		}
	}
}