package com.ilme.env;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author iwk
 * @date 2020/4/2 10:54 上午
 * @since 1.0.0
 **/
@Getter
@Setter
@Builder
@ToString
public class User implements Serializable {

	private Integer id;

	private Integer typeCode;

	private UserType userType;

	private String name;

}
