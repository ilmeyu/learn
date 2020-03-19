package com.ilme.t20.t2003;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;

/**
 * 工作流
 *
 * @author ilme
 * @date 2020/3/18 10:27 上午
 **/
@Slf4j
public class T181027 {

	public static void main(String[] args) {
		ProcessEngineConfiguration processEngineConfiguration =
				ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("t202003/T181027_activiti.cfg.xml");

		ProcessEngine processEngine = processEngineConfiguration.buildProcessEngine();
	}

}
