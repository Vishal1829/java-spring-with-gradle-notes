package org.learn.springbootlearning.controller;

import lombok.extern.slf4j.Slf4j;
import org.learn.springbootlearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/")
@Slf4j
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/getuser")
    public String getUserMethod() {
        log.info("inside getUserMethod: {}", Thread.currentThread().getName());
        userService.asyncMethodTest();
        return null;
    }
}
