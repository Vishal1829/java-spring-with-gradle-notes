Async Annotation:-
- Used to mark method that should run asynchronously
- Runs in a new thread, without blocking the main thread.

If we see SpringBoot framework code, it first looks for defaultExecutor found,
only then SimpleAsyncTaskExecutor is used.

ThreadPoolTaskExecutor is nothing but a Spring boot Object, which is just a
wrapper around Java ThreadPoolExecutor.

Note:-
ThreadPoolExecutor (this is java)
ThreadPoolTaskExecutor (this is spring:- it is a spring wrapper around java 
ThreadPoolExecutor)


### Conditions for @Async Annotation to work properly?
1. Different Class: If @Async annotation is applied to the method within the same
class from which it is being called, then Proxy mechanism is skipped because
internally method calls are NOT INTERCEPTED.
2. Public method: Method annotated with @Async must be public. And again, AOP
interception works only on Public methods.


So whenever we use @Async AOP gets involved.
AOP steps:
1. Intercept (and once it is intercept it calls the proxy)
2. Proxy (then internally Proxy might call your method)
   So to make this interception work these 2 above conditions(in SS) is required,
   interception cannot work on private or protected methods it only works on public methods
   and 2nd interception only works when from class1 to class2 (different classes get
   invoked). So if in the same class u r calling the method interception will not be even
   in the picture. So only when u r calling from like u have one method in class1 and u r
   invoking a method2 which is in class2 then only interception comes into the picture.
   Interception works only when these 2 conditions are met and becoz of that whenever we
   use @Async annotation these 2 condtions should be met.


https://www.notion.so/SpringBoot-Notes5-11c25e6c65438002a50cdd8be342a548
