package com.rajtechnologies.springbootsecurity.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SpringSecurityController {

    @GetMapping("/welcome")
    public String welcome(){
        return "Welcome to Spring Security";
    }

    @GetMapping("/admin")
    public String admin(){
        return "Welcome to Admin Page";
    }

    @GetMapping("/greet")
    public String greet(){
        return "Welcome to Greet Page";
    }
}
