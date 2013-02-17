package com.nogoon.hadoop.manager.config;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import org.thymeleaf.spring3.SpringTemplateEngine;
import org.thymeleaf.spring3.view.ThymeleafViewResolver;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

@Configuration
@EnableWebMvc
@ImportResource(value="classpath:applicationContext.xml")
@ComponentScan({ "com.nogoon.hadoop.manager.web.controller",
		"com.nogoon.hadoop.manager.service" })
public class MainConfig extends WebMvcConfigurationSupport {

	@Bean
	public static InternalResourceViewResolver jspViewResolver() {
		InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		resolver.setViewClass(JstlView.class);
		resolver.setPrefix("/WEB-INF/view/jsp/");
		resolver.setSuffix(".jsp");
		resolver.setOrder(2);

		return resolver;
	}
	
	@Bean
	public static ServletContextTemplateResolver thymeleafTemplateResolver() {
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver();
		templateResolver.setPrefix("/WEB-INF/view/");
		templateResolver.setSuffix(".html");
		templateResolver.setCharacterEncoding("UTF-8");
		templateResolver.setTemplateMode("HTML5");
		templateResolver.setCacheable(false);
		
		return templateResolver;
	}

	@Bean
	public static ThymeleafViewResolver thymeleafViewResolver() {

		SpringTemplateEngine engine = new SpringTemplateEngine();
		engine.setTemplateResolver(thymeleafTemplateResolver());

		ThymeleafViewResolver resolver = new ThymeleafViewResolver();
		resolver.setTemplateEngine(engine);
		resolver.setOrder(1);
		resolver.setCharacterEncoding("UTF-8");
		resolver.setViewNames(new String[] { "thymeleaf/*" });

		return resolver;
	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		//registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Override
	protected void addArgumentResolvers(
			List<HandlerMethodArgumentResolver> argumentResolvers) {
		super.addArgumentResolvers(argumentResolvers);

		// 사용자정보
		// argumentResolvers.add(new LoginParamArgumentResolver());

	}
}
