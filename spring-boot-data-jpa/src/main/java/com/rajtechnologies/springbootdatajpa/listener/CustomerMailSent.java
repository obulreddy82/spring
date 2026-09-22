package com.rajtechnologies.springbootdatajpa.listener;

import com.rajtechnologies.springbootdatajpa.event.CustomerEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class CustomerMailSent {
    @Async
    @EventListener
    //@EventListener(condition = "#event.customerId > 3")
    public void sendCustomerMail(CustomerEvent event){
        System.out.println("Inside Listener::::"+Thread.currentThread().getName());
        System.out.println("Customer Mail Sent");
        System.out.println("Customer Mail:"+event.customerId);
    }
}
