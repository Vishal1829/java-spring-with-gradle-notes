package org.learn.springbootlearning.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class UserService {

//    both will work as only one executor bean is present in config but if we provide more than one bean in config
//    then we will get ambiguity error, so we have to provide a name to bean and wherever we use @Async there we have to
//    mention that executor bean which we want to use.
//    @Async("myThreadPoolTaskExecutor")
//    @Async("myThreadPoolExecutor")
    @Async
    public void asyncMethodTest() {
        log.info("inside asyncMethodTest: {}", Thread.currentThread().getName());
        try {
            Thread.sleep(50000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
/*
Async Annotation:-
- Used to mark method that should run asynchronously
- Runs in a new thread, without blocking the main thread.

During Application startup, Spring boot sees that, ThreadPoolTaskExecutor Bean present, so it makes
it default only.

And even when we use @Async without any name, our "myThreadPoolTaskExecutor" will get picked only.
Note: it will only be when only one ThreadPoolTaskExecutor Bean is present.
 */
