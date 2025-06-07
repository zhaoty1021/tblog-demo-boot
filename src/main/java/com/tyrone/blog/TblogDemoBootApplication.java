package com.tyrone.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tyrone.blog.mapper") // 仅扫描 MyBatis 的 mapper
public class TblogDemoBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TblogDemoBootApplication.class, args);
	}

}
