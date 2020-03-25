package com.ilme.complete.activiti;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.io.File;
import java.io.InputStream;
import java.util.List;

/**
 * 工作流基础功能测试
 *
 * 使用教程搜索本机文档 'SaaS-IHRM项目'
 * @author ilme
 * @date 2020/3/18 10:27 上午
 **/
@Test
@Slf4j
@ContextConfiguration(locations = {"classpath:complete/activiti/T2003181027_activiti.cfg.xml"})
public class T2003181027 extends AbstractTestNGSpringContextTests {

	/**
	 * 工作流引擎，相当于一个门面接口，通过 ProcessEngineConfiguration <br />
	 * 创建 processEngine，通过 ProcessEngine 创建各个 service 接口。
	 */
	@Autowired
	ProcessEngine processEngine;

	/**
	 * repositoryService 是 activiti 的资源管理类，提供了管理和控制流程发布包和流程定义的操作。<br />
	 * 使用工作流建模工 具设计的业务流程图需要使用此 service 将流程定义文件的内容部署到计算机。<br />
	 * 除了部署流程定义以外还可以: <br />
	 * <ul>
	 * <li>查询引擎中的发布包和流程定义。</li>
	 * <li>暂停或激活发布包，对应全部和特定流程定义。 暂停意味着它们不能再执行任何操作了，激活是对应的反向操作。</li>
	 * <li>获得多种资源，像是包含在发布包里的文件， 或引擎自动生成的流程图。 </li>
	 * <li>获得流程定义的 pojo 版本， 可以用来通过 java 解析流程，而不必通过 xml。</li>
	 * </ul>
	 */
	@Autowired
	RepositoryService repositoryService;

	/**
	 * 它是 activiti 的流程运行管理类。可以从这个服务类中获取很多关于流程执行相关的信息
	 */
	@Autowired
	RuntimeService runtimeService;

	/**
	 * activiti 的任务管理类。可以从这个类中获取任务的信息。
	 */
	@Autowired
	TaskService taskService;

	/**
	 * activiti 的历史管理类，可以查询历史信息，执行流程时，引擎会保存很多数据(根据配置)，<br />
	 * 比如流程实例启动时间，任务的参与者， 完成任务的时间，每个流程实例的执行路径，等等。 <br />
	 * 这个服务主要通过查询功能来获得这些数据。<br />
	 */
	@Autowired
	HistoryService historyService;

	/**
	 * activiti的引擎管理类，提供了对 Activiti 流程引擎的管理和维护功能，<br />
	 * 这些功能不在工作流驱动 的应用程序中使用，主要用于 Activiti 系统的日常维护。
	 */
	@Autowired
	ManagementService managementService;

	@Test
	public void init() {
		log.debug("流程引擎名: {}", processEngine.getName());
		log.debug("流程资源服务: {}", repositoryService);
		log.debug("流程运行时服务: {}", runtimeService);
		log.debug("流程任务服务: {}", taskService);
		log.debug("流程历史服务: {}", historyService);
	}

	// 部署流程
	@Test
	public void deploy() {
		Deployment deployment = repositoryService
			.createDeployment()
			.addClasspathResource("complete/activiti/T2003181027_holiday.bpmn")
			.key("holiday")
			.name("请假申请流程")
			.deploy();

		log.info("流程部署id: {}", deployment.getId());
		log.info("流程部署名称: {}", deployment.getName());
	}

	// 启动流程
	@Test
	public void startProcess() {
		RuntimeService runtimeService = processEngine.getRuntimeService();

		ProcessInstance processInstance
		= runtimeService.startProcessInstanceByKey("process-3d6fb4f9-0246-4797-bca3-e3eb254a6b44");

		log.info("流程定义id: {}", processInstance.getProcessDefinitionId());
		log.info("流程实例id: {}", processInstance.getProcessInstanceId());
		log.info("当前活动id: {}", processInstance.getActivityId());
	}

	// 查询任务列表
	@Test
	public void findPersonalTaskList() {
		String assignee = "zhangsan";

		TaskService taskService = processEngine.getTaskService();

		List<Task> taskList = taskService
			.createTaskQuery()
			.processDefinitionKey("process-3d6fb4f9-0246-4797-bca3-e3eb254a6b44")
			.taskAssignee(assignee)
			.list();

		taskList.forEach(task -> {
			log.info("任务id: {}", task.getId());
			log.info("任务代理人: {}", task.getAssignee());
			log.info("任务名称: {}", task.getName());
		});

	}

	// 流程任务通过
	@Test
	public void completeTask() {
		TaskService taskService = processEngine.getTaskService();
		taskService.complete("75006");
	}

	// 流程定义查询
	@Test
	public void queryProcessDefinition() {
		String processDefinitionKey = "process-3d6fb4f9-0246-4797-bca3-e3eb254a6b44";

		RepositoryService repositoryService = processEngine.getRepositoryService();

		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();

		List<ProcessDefinition> processDefinitionList = processDefinitionQuery
			.processDefinitionKey(processDefinitionKey)
			.orderByProcessDefinitionVersion()
			.desc()
			.list();

		processDefinitionList.forEach(definition -> {
			log.info("===================================");
			log.info("流程部署id: {}", definition.getDeploymentId());
			log.info("流程定义id: {}", definition.getId());
			log.info("流程定义key: {}", definition.getKey());
			log.info("流程定义版本: {}", definition.getVersion());
		});
	}

	// 删除流程定义
	@Test
	public void deleteDeployment() {
		RepositoryService repositoryService = processEngine.getRepositoryService();

		repositoryService.deleteDeployment("1");
	}

	// 查询流程实例历史
	@Test
	public void queryProcessInstanceHistory() {
		HistoryService historyService = processEngine.getHistoryService();

		List<HistoricActivityInstance> historicActivityInstanceList = historyService
			.createHistoricActivityInstanceQuery()
			.processInstanceId("75002")
			.list();

		historicActivityInstanceList.forEach(instance -> {
			log.info("流程实例id: {}", instance.getActivityId());
			log.info("流程实例名称: {}", instance.getActivityName());
			log.info("审批人: {}", instance.getAssignee());
		});
	}

	// 流程定义资源查询
	@Test
	public void getProcessResources() throws Exception {
		String processDefinitionId = "process-3d6fb4f9-0246-4797-bca3-e3eb254a6b44:1:72504";

		RepositoryService repositoryService = processEngine.getRepositoryService();

		ProcessDefinition processDefinition = repositoryService
			.createProcessDefinitionQuery()
			.processDefinitionId(processDefinitionId)
			.singleResult();

		String processDefinitionResourceName = processDefinition.getResourceName();

		String diagramResourceName = processDefinition.getDiagramResourceName();

		log.info("流程定义文件: {}", processDefinitionResourceName);
		log.info("流程定义图: {}", diagramResourceName);

		if (StringUtils.isBlank(processDefinitionResourceName)) {
			return;
		}

		String parentPath = "src/main/resources/";
		String suffix = "_out";

		String outputProcessDefinitionFileName = new StringBuilder(processDefinitionResourceName).insert(
			processDefinitionResourceName.indexOf('.'),
			suffix
		).toString();

		File processDefinitionResourceFile = new File(parentPath + outputProcessDefinitionFileName);
		log.info("当前父目录: {}", processDefinitionResourceFile.getAbsolutePath());
		if (!processDefinitionResourceFile.createNewFile()) {
			return;
		}

		InputStream resourceAsStream
			= repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), processDefinitionResourceName);

		FileUtils.copyInputStreamToFile(resourceAsStream, processDefinitionResourceFile);
//
//		FileOutputStream fileOutputStream = new FileOutputStream(processDefinitionResourceFile);
//
//		try {
//			byte[] bytes = new byte[1024];
//			int len = -1;
//			while ((len = resourceAsStream.read(bytes, 0, bytes.length)) != -1) {
//				fileOutputStream.write(bytes, 0, len);
//			}
//		} finally {
//			try {
//				fileOutputStream.close();
//			} finally {
//				resourceAsStream.close();
//			}
//		}

	}

}
