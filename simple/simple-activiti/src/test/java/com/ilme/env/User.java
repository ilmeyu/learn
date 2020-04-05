package com.ilme.env;

import lombok.*;

import java.io.Serializable;

/**
 * @Author 余文楷
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
