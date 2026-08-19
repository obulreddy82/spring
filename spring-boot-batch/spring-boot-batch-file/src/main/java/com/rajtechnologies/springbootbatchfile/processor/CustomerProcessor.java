package com.rajtechnologies.springbootbatchfile.processor;

import com.rajtechnologies.springbootbatchfile.model.Customer;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;

public class CustomerProcessor implements ItemProcessor<Customer, Customer> {
    //process can validate, transform, enrich and filter
    @Override
    public @Nullable Customer process(Customer customer) throws Exception {
        //validate email
        if (customer.getEmail() == null ||
                customer.getEmail().isBlank()) {
            throw new IllegalArgumentException("Email is missing for customer" + customer.getId());
        }

        //validate age
        if (customer.getAge() == null || customer.getAge() < 18) {
            throw new IllegalArgumentException("Customer age is invalid for customer" + customer.getId());
        }

        //transformation
        customer.setName(customer.getName().toUpperCase());
        return customer;
    }
}
