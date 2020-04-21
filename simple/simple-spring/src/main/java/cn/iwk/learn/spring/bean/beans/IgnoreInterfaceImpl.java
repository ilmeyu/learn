package cn.iwk.learn.spring.bean.beans;

import lombok.ToString;

import java.util.List;
import java.util.Set;

/**
 * @Author iwk
 * @date 2020/4/6 9:40 下午
 * @since 1.0.0
 **/
@ToString
public class IgnoreInterfaceImpl implements IgnoreInterface {

	List<String> list;

	Set<String> set;

	@Override
	public void setList(List<String> list) {
		this.list = list;
	}

	@Override
	public void setSet(Set<String> set) {
		this.set = set;
	}

}
