package com.joseph.quotes;

import com.joseph.quotes.configuration.JpaConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;


@Import(JpaConfiguration.class)
@SpringBootApplication(scanBasePackages={"com.joseph.quotes"})// same as @Configuration @EnableAutoConfiguration @ComponentScan
public class QuotesApp {

	public static void main(String[] args) {
		SpringApplication.run(QuotesApp.class, args);
	}
}
