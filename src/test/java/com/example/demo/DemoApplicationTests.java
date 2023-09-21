package com.example.demo;

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import com.example.demo.DemoApplicationTests.ServiceConfiguration;

@SpringJUnitConfig(classes = ServiceConfiguration.class)
class DemoApplicationTests {

	@Autowired
	private DemoService demoService;

	@Test
	void whenThenThrowTest() throws InterruptedException, ExecutionException {
		when(demoService.someAsyncTask()).thenThrow(RuntimeException.class);
		Assertions.assertThrows(RuntimeException.class, () -> demoService.someAsyncTask());
	}

	@Test
	void doThrowWhenTest() throws InterruptedException, ExecutionException {
		Mockito.doThrow(RuntimeException.class).when(demoService).someAsyncTask();
		Assertions.assertThrows(RuntimeException.class, () -> demoService.someAsyncTask());
	}

	@EnableAsync
	@Configuration
	static class ServiceConfiguration {

		@Bean
		DemoService demoService() {
			return spy(DemoService.class);
		};

	}

	static class DemoService {

		@Async
		public CompletableFuture<Void> someAsyncTask() {
			return CompletableFuture.completedFuture(null);
		}

	}

}