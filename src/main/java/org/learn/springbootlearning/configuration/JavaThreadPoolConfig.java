package org.learn.springbootlearning.configuration;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

@Configuration
public class JavaThreadPoolConfig implements AsyncConfigurer {

    private ThreadPoolExecutor poolExecutor;
    @Override
    public synchronized Executor getAsyncExecutor() {

        if (poolExecutor == null) {
            int minPoolSize = 2;
            int maxPoolSize = 4;
            int queueSize = 3;
            poolExecutor = new ThreadPoolExecutor(minPoolSize, maxPoolSize, 1,
                    TimeUnit.HOURS, new ArrayBlockingQueue<>(queueSize), new CustomThreadFactory());
        }
        return poolExecutor;
    }
}

class CustomThreadFactory implements ThreadFactory {

    private final AtomicInteger threadNo = new AtomicInteger(1);
    @Override
    public Thread newThread(@NotNull Runnable r) {
        Thread thread = new Thread(r);
        thread.setName("MyJavaAsyncThread-"+threadNo.getAndIncrement());
        return thread;
    }
}

/*
During Application startup, Spring boot sees that, ThreadPoolExecutor (java one)Bean is present, so it do not
create its own default ThreadPoolTaskExecutor (spring wrapper one) instead it set the default executor to
"SimpleAsyncTaskExecutor". So, therefore out java executor bean will not get picked up so inorder to get this one
picked:
So, whenever we have defined our own ThreadPoolExecutor (Java one), always specify the name also with @Async annotation.

Note:-
If we always want to set out provided executor as default one, even if anyone use @Async, our executor only
should be picked then implement AsyncConfigurer and override getAsyncExecutor and provide the java bean as above done.
 */