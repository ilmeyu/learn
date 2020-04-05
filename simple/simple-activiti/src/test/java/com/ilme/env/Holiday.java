package com.ilme.env;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * @Author 余文楷
 * @date 2020/4/2 11:17 上午
 * @since 1.0.0
 **/
@Getter
@Setter
@Builder
public class Holiday implements Serializable {

	private Integer id;

	private String businessKey;

	private String reason;

	private Integer days;

	private Integer userId;

}
