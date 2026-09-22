package com.rajtechnologies.springbootdatajpa.listener;

import com.rajtechnologies.springbootdatajpa.event.CustomerEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
public class CustomerIdListen {

    //@EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void listenCustomerData(CustomerEvent event){
        // Execute only after successful commit
        System.out.println("Inside listenCustomerData:::"+Thread.currentThread().getName());
        System.out.println("Customer Data Sent");
        System.out.println("Customer Data:"+event.customerId);
    }
}
