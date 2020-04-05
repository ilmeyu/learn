package com.ilme.demo;

import com.ilme.env.DataUtil;
import com.ilme.env.Holiday;
import com.ilme.env.User;
import com.ilme.env.UserType;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.history.HistoricActivityInstance;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.JavaVersion;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 工作流基础功能测试
 *
 * 使用教程搜索本机文档 'SaaS-IHRM项目'
 * @author ilme
 * @date 2020/3/18 10:27 上午
 **/
@Test
@Slf4j
@ContextConfiguration(locations = {"classpath:activiti.cfg.xml"})
public class ActivitiDemo extends AbstractTestNGSpringContextTests {

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
	 * <li>查询引擎中的发布包和流程定义。</li>
	 * <li>暂停或激活发布包，对应全部和特定流程定义。 暂停意味着它们不能再执行任何操作了，激活是对应的反向操作。</li>
	 * <li>获得多种资源，像是包含在发布包里的文件， 或引擎自动生成的流程图。 </li>
	 * <li>获得流程定义的 pojo 版本， 可以用来通过 java 解析流程，而不必通过 xml。</li>
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

	/**
	 * 流程部署信息
	 */
	Deployment deployment;

	/**
	 * 流程定义信息
	 */
	ProcessDefinition processDefinition;

	/**
	 * 流程实例信息
	 */
	ProcessInstance processInstance;

	public void init() {
		log.debug("工作流启动成功");

		deployment = repositoryService
			.createDeploymentQuery()
			.processDefinitionKey("process-db6c1bd3-3a90-47fa-9603-325416601f2d")
			.singleResult();

		if (deployment == null) {
			log.error("流程部署失败");
			return;
		}

		processDefinition = repositoryService
			.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

		log.debug("流程引擎名: {}", processEngine.getName());
		log.debug("流程资源服务: {}", repositoryService);
		log.debug("流程运行时服务: {}", runtimeService);
		log.debug("流程任务服务: {}", taskService);
		log.debug("流程历史服务: {}", historyService);
	}

//	// 部署流程
//	@Deprecated // 自动部署
//	@Test
//	public void deploy() {
//		Deployment deployment = repositoryService
//			.createDeployment()
//			.addClasspathResource("complete/activiti/holiday.bpmn")
//			.key("holiday")
//			.name("请假申请流程")
//			.deploy();
//
//		log.info("流程部署id: {}", deployment.getId());
//		log.info("流程部署名称: {}", deployment.getName());
//	}

	// 创建流程
	public void createProcess() {
		// 业务key关联实际业务
		String business = UUID.randomUUID().toString().replace("-", "");

		User user = DataUtil.getUserByTypeSingleResult(UserType.STAFF);

		Map<String, Object> variables = new HashMap<>();
		variables.put("user", user);

		processInstance = runtimeService.startProcessInstanceById(processDefinition.getId(), business, variables);

		log.info("流程定义id: {}", processInstance.getProcessDefinitionId());
		log.info("流程实例id: {}", processInstance.getProcessInstanceId());
		log.info("流程活动id: {}", processInstance.getActivityId());
		log.info("流程业务key: {}", processInstance.getBusinessKey());
		log.info("流程参数: {}", processInstance.getProcessVariables());
	}

	// 查询任务列表
	public void taskList() {

		User staff = DataUtil.getUserByTypeSingleResult(UserType.STAFF);

		List<Task> taskList = taskService
			.createTaskQuery()
			.processDefinitionId(processDefinition.getId())
			.taskAssignee(staff.getName())
			.list();

		taskList.forEach(task -> {
			log.info("任务id: {}", task.getId());
			log.info("任务代理人: {}", task.getAssignee());
			log.info("任务名称: {}", task.getName());
		});

	}

	// 流程任务处理
	public void holidayTaskHandler() {
		// 员工
		User staff = DataUtil.getUserByTypeSingleResult(UserType.STAFF);
		// 经理
		User manager = DataUtil.getUserByTypeSingleResult(UserType.MANAGER);
		// 总经理
		User gm = DataUtil.getUserByTypeSingleResult(UserType.GM);
		// 人事
		User hr = DataUtil.getUserByTypeSingleResult(UserType.HR);
		// 当前流程任务查询器
		TaskQuery taskQuery = taskService.createTaskQuery().processInstanceId(processInstance.getProcessInstanceId());
		Task task = taskQuery.singleResult();

		Holiday holiday = Holiday.builder().id(1000001).reason("请假理由").days(5).userId(staff.getId()).build();

		Map<String, Object> variables = new HashMap<>();
		variables.put("holiday", holiday);

		// 填写申请单
		taskService.setAssignee(task.getId(), String.valueOf(staff.getId()));
		taskService.complete(task.getId(), variables);
		// 经理审批
		task = taskQuery.singleResult();
		taskService.setAssignee(task.getId(), String.valueOf(manager.getId()));
		taskService.complete(task.getId());
		// 总经理审批
		task = taskQuery.singleResult();
		taskService.setAssignee(task.getId(), String.valueOf(gm.getId()));
		taskService.complete(task.getId());
		// 人事
		task = taskQuery.singleResult();
		taskService.setAssignee(task.getId(), String.valueOf(hr.getId()));
		taskService.complete(task.getId());
	}

	// 流程定义查询
	public void queryProcessDefinition() {
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();

		List<ProcessDefinition> processDefinitionList = processDefinitionQuery
			.processDefinitionId(processDefinition.getId())
			.orderByProcessDefinitionVersion()
			.desc()
			.list();

		processDefinitionList.forEach(definition -> {
			log.info("流程部署id: {}", definition.getDeploymentId());
			log.info("流程定义id: {}", definition.getId());
			log.info("流程定义key: {}", definition.getKey());
			log.info("流程定义版本: {}", definition.getVersion());
		});
	}

//	// 删除流程定义
//	@Deprecated // 见流程清理
//	@Test
//	public void deleteDeployment() {
//		RepositoryService repositoryService = processEngine.getRepositoryService();
//
//		repositoryService.deleteDeployment("1");
//	}

	// 查询流程实例历史
	public void queryProcessInstanceHistory() {
		List<HistoricActivityInstance> historicActivityInstanceList = historyService
			.createHistoricActivityInstanceQuery()
			.processInstanceId(processInstance.getProcessInstanceId())
			.list();

		historicActivityInstanceList.forEach(instance -> {
			log.debug("流程实例id: {}", instance.getActivityId());
			log.debug("任务id: {}", instance.getTaskId());
			log.debug("流程实例名称: {}", instance.getActivityName());

			if (StringUtils.isNotBlank(instance.getAssignee())) {
				log.debug("代理人信息: {}", DataUtil.getUserById(Integer.valueOf(instance.getAssignee())));
			}

			log.debug("--------------------------");
		});
	}

	// 获取流程定义资源
	public void getProcessResources() throws Exception {
		String resourceName = processDefinition.getResourceName();

		String diagramResourceName = processDefinition.getDiagramResourceName();

		log.info("流程定义文件: {}", resourceName);
		log.info("流程定义图: {}", diagramResourceName);

		if (StringUtils.isBlank(resourceName)) {
			return;
		}

		String parentPath = "target/generated-sources/";

		File resourceFile = new File(parentPath + processDefinition.getName());
		log.debug("目标路径: {}", resourceFile.getAbsolutePath());

		if (! resourceFile.getParentFile().exists()) {
			if (! (resourceFile.getParentFile().mkdirs() && resourceFile.createNewFile())) {
				return;
			}
		}

		InputStream resourceAsStream
			= repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);

		FileUtils.copyInputStreamToFile(resourceAsStream, resourceFile);

	}

	public void clean() {
		log.debug("清理工作");
		List<Deployment> list = repositoryService.createDeploymentQuery().list();

		list.forEach(deployment -> {
			repositoryService.deleteDeployment(deployment.getId(), true);
		});
	}

}
