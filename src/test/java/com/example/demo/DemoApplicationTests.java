package com.example.demo;

import static org.mockito.Mockito.when;

import java.util.concurrent.ExecutionException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.SpyBean;

@SpringBootTest
class DemoApplicationTests {
	@SpyBean
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
}
