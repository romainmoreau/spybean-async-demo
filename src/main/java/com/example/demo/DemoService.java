package com.example.demo;

import java.util.concurrent.CompletableFuture;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class DemoService {
    @Async
    public CompletableFuture<Void> someAsyncTask() {
        return CompletableFuture.completedFuture(null);
    }
}
