package com.r2s;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
public class BookShopApplication {

	public static void main(String[] args) {
		SpringApplication.run(BookShopApplication.class, args);
	}

}
