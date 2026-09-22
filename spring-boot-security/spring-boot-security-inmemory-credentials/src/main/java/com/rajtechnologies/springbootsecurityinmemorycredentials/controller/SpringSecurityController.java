package com.rajtechnologies.springbootsecurityinmemorycredentials.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class SpringSecurityController {

    @RequestMapping("/welcome")
    public String welcome(){
        return "Welcome to Spring Security";
    }

    @RequestMapping("/greet")
    public String greet(){
        return "Welcome to Greet Page";
    }

}
