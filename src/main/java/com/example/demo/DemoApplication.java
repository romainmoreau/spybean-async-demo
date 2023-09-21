package com.example.demo;

import java.util.concurrent.Executor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class DemoApplication implements AsyncConfigurer {
	private static final Logger LOGGER = LoggerFactory.getLogger(DemoApplication.class);

	@Override
	public Executor getAsyncExecutor() {
		return (command) -> {
			throw new IllegalStateException("No thread available");
		};
	}

	public static void main(String[] args) {
		var demoService = SpringApplication.run(DemoApplication.class, args).getBean(DemoService.class);
		try {
			demoService.someAsyncTask();
		} catch (IllegalStateException e) {
			LOGGER.error("Synchronous exception example", e);
		}
	}
}
