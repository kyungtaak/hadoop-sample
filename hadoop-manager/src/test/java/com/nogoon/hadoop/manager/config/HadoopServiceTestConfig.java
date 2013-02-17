package com.nogoon.hadoop.manager.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

@Configuration
@ImportResource(value="classpath:applicationContext.xml")
@ComponentScan(basePackages={"com.nogoon.hadoop.manager.service"})
public class HadoopServiceTestConfig {

}
