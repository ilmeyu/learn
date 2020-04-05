package com.ilme.util;

import com.baomidou.mybatisplus.generator.AutoGenerator;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * Mybatis代码生成器
 *
 * @author ilme
 * @date 2020/3/21 3:09 下午
 **/
public class MybatisPlusGenerator {

	public static void main(String[] args) {

		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("mybatisPlusGenerator_application.xml");

		AutoGenerator autoGenerator = applicationContext.getBean(AutoGenerator.class);

		autoGenerator.execute();

//		AutoGenerator autoGenerator = new AutoGenerator();
//
//		String projectPath = "/Users/ilem/TEMP";
//
//		GlobalConfig globalConfig = new GlobalConfig();
//		globalConfig.setOutputDir(projectPath + "/src/main/java");
//		globalConfig.setAuthor("ILME");
//		globalConfig.setOpen(Boolean.FALSE);
//		globalConfig.setSwagger2(Boolean.TRUE);
//		globalConfig.setActiveRecord(true);
//
//		autoGenerator.setGlobalConfig(globalConfig);
//
//		DataSourceConfig dataSourceConfig = new DataSourceConfig();
//		dataSourceConfig.setDbType(DbType.MYSQL);
//		dataSourceConfig.setDriverName("com.mysql.jdbc.Driver");
//		dataSourceConfig.setUrl("jdbc:mysql://39.105.39.137:3306/old_supplier?characterEncoding=utf8");
//		dataSourceConfig.setUsername("root");
//		dataSourceConfig.setPassword("2459786062");
//
//		autoGenerator.setDataSource(dataSourceConfig);
//
//		PackageConfig packageConfig = new PackageConfig();
//		packageConfig.setModuleName("demo");
//		packageConfig.setParent("com.ilme");
//
//		autoGenerator.setPackageInfo(packageConfig);
//
//		TemplateConfig templateConfig = new TemplateConfig();
//
//		autoGenerator.setTemplate(templateConfig);
//
//		// 策略
//		StrategyConfig strategyConfig = new StrategyConfig();
//		strategyConfig.setNaming(NamingStrategy.underline_to_camel);
//		strategyConfig.setColumnNaming(NamingStrategy.underline_to_camel);
//		strategyConfig.setSuperEntityClass(Object.class);
//		strategyConfig.setEntityLombokModel(Boolean.TRUE);
//		strategyConfig.setRestControllerStyle(Boolean.TRUE);
//
//		autoGenerator.setStrategy(strategyConfig);
//		autoGenerator.setTemplateEngine(new FreemarkerTemplateEngine());
//
//		autoGenerator.execute();
	}

}
