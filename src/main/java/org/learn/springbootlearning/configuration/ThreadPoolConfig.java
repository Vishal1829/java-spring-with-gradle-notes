package org.learn.springbootlearning.configuration;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class ThreadPoolConfig {

    @Bean(name = "myThreadPoolTaskExecutor")
    public Executor taskPoolExecutor() {
        int minPoolSize = 2;
        int maxPoolSize = 4;
        int queueSize = 3;

        ThreadPoolTaskExecutor poolTaskExecutor = new ThreadPoolTaskExecutor();
        poolTaskExecutor.setCorePoolSize(minPoolSize);
        poolTaskExecutor.setMaxPoolSize(maxPoolSize);
        poolTaskExecutor.setQueueCapacity(queueSize);
        poolTaskExecutor.setThreadNamePrefix("MyAsyncThread-");
        poolTaskExecutor.initialize();
        return poolTaskExecutor;
    }

//    @Bean(name = "myThreadPoolExecutor")
//    public Executor taskPoolExecutor() {
//        int minPoolSize = 2;
//        int maxPoolSize = 4;
//        int queueSize = 3;
//
//        ThreadPoolExecutor poolTaskExecutor = new ThreadPoolExecutor(minPoolSize, maxPoolSize,
//                1, TimeUnit.HOURS, new ArrayBlockingQueue<>(queueSize), new CustomThreadFactory());
//
//        return poolTaskExecutor;
//    }

}

//class CustomThreadFactory implements ThreadFactory {
//
//    private final AtomicInteger threadNo = new AtomicInteger(1);
//    @Override
//    public Thread newThread(@NotNull Runnable r) {
//        Thread thread = new Thread(r);
//        thread.setName("MyJavaAsyncThread-"+threadNo.getAndIncrement());
//        return thread;
//    }
//}
