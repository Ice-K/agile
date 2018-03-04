package com.ice.agile;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan// 设置启动时spring能够扫描到我们自己编写的servlet和filter, 用于Druid监控
@MapperScan("com.ice.agile.**.mapper")// 用于扫描mapper接口
public class AgileApplication {

	public static void main(String[] args) {
		SpringApplication.run(AgileApplication.class, args);
	}
}
