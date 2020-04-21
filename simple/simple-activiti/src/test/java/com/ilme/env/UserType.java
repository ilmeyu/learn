package com.ilme.env;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @Author iwk
 * @date 2020/4/2 10:56 上午
 * @since 1.0.0
 **/
@Getter
@NoArgsConstructor
@AllArgsConstructor
public enum UserType {

	STAFF(1),
	MANAGER(2),
	GM(3),
	HR(4),
	FINANCE(5)
	;

	Integer typeCode;

}
