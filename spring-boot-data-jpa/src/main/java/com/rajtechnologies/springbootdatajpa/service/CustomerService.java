package com.rajtechnologies.springbootdatajpa.service;

import com.rajtechnologies.springbootdatajpa.entity.Customer;
import com.rajtechnologies.springbootdatajpa.model.CustomerRequest;
import com.rajtechnologies.springbootdatajpa.model.CustomersRequest;
import com.rajtechnologies.springbootdatajpa.repository.CustomerRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {
    @PostConstruct
    public void init() {
        System.out.println("Customer Service Init");
    }
    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        System.out.println("Customer Service Constructor");
        this.customerRepository = customerRepository;
    }

    public Customer saveCustomer(CustomerRequest customerRequest) {

        customerRepository.save(customerRequest.getCustomer());
        return customerRequest.getCustomer();
    }

    public List<Customer> saveCustomers(CustomersRequest customersRequest) {
        customersRequest.getCustomers().stream().forEach(customer ->
                customer.getOrders()
                        .forEach(order -> order.setCustomer(customer)));
        customerRepository.saveAll(customersRequest.getCustomers());
        return customersRequest.getCustomers();
    }

    public List<Customer> getAllCustomers() {

        //return customerRepository.findAll();
        //To resolve N+1 problem , you can use the Entity Graph
        //There are 3 days we can resolve the N+1 problem
        //1. JOIN FETCH , need to create the JPQL query
        //2. @EntityGraph(attributePaths="orders")
        //3. @BatchSize(10), need to add inside customer entity


        // return customerRepository.findAllWithOrders();
        return customerRepository.findAll();
    }

    @Transactional
    public void updateCustomer(Long id, Customer customer) {
        customerRepository.findById(id)
                .ifPresent(c -> c.setName(customer.getName()));
    }
    @PreDestroy
    public void destroy() {
        System.out.println("Customer Service Destroy");
    }
}
