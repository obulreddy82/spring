package com.rajtechnologies.springbootdatajpa.config;

import org.aspectj.lang.annotation.*;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Component;

@Aspect
@Component
@EnableAsync
public class LoggingAspect {

    @Before("execution(* com.rajtechnologies.springbootdatajpa.service.*.*(..))")
    public void logBefore() {
        System.out.println("Method Started");
    }
    @After("execution(* com.rajtechnologies.springbootdatajpa.service.*.*(..))")
    public void logAfter(){
        System.out.println("Method Ended");
    }
//    @Before
//    @After
//    @Around()
//    @AfterReturning
//    @AfterThrowing

    //Logging
    //Transactions
    //Security
    //Performance Monitoring
    //Auditing

}
