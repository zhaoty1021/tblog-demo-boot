package com.tyrone.blog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.tyrone.blog.mapper")
public class TblogDemoBootApplication {

	public static void main(String[] args) {
		SpringApplication.run(TblogDemoBootApplication.class, args);
	}

}
