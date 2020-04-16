package com.ilme.t20.t2002;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

/**
 * thymeleaf 使用入门
 *
 * @author ilme
 * @date 2020/2/19 7:32 下午
 **/
@Slf4j
public class T191932 {

	static final String FILE = "target/generated-sources/T191932_test.html";
	static final String SUFFIX = ".html";
	static final String TEMPLATE = "t20/t2002/T191932_test";

	static {
		File file = new File(FILE);
		if (!file.getParentFile().exists()) {
			try {
				if (file.mkdirs() && file.exists()) {
					file.createNewFile();
					log.debug("filepath: {}", file.getAbsolutePath());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		log.info("parent path: {}", file.getAbsolutePath());
	}

	public static void main(String[] args) throws Exception {
		// 1. 上下文
		Context context = new Context();
		// 2. 创建数据模型
		Map<String, Object> dataModel = ImmutableMap.of("name", "Hello Thymeleaf!");
		context.setVariables(dataModel);
		// 3. 准备文件
		File dest = new File(FILE);
		// 4. 生成页面
		PrintWriter writer = new PrintWriter(dest, "UTF-8");
		// 模版解析器
		ClassLoaderTemplateResolver resolver = new ClassLoaderTemplateResolver();
		// 模版模型
		resolver.setTemplateMode(TemplateMode.HTML);
		// 后缀
		resolver.setSuffix(SUFFIX);
		// 创建模版引擎
		TemplateEngine engine = new TemplateEngine();
		// 设置模版解析器
		engine.setTemplateResolver(resolver);
		// 执行模版引擎
		engine.process(TEMPLATE, context, writer);
	}

}
