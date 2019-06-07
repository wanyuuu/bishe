package com.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.servlet.MultipartConfigElement;

@SpringBootApplication
@EnableJpaRepositories
@ComponentScan(basePackages = "com.demo")
public class DesignDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DesignDemoApplication.class, args);
	}
	//处理文件上传
//	@Bean(name = "multipartResolver")
//	public MultipartResolver multipartResolver(){
//		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//		resolver.setDefaultEncoding("UTF-8");
//		//推迟文件解析 并能俘获文件大小异常
//		resolver.setResolveLazily(true);
//		resolver.setMaxUploadSizePerFile(5*1024*1024);
//		resolver.setMaxUploadSize(100*1024*1024);
//		return resolver;
//	}
}
