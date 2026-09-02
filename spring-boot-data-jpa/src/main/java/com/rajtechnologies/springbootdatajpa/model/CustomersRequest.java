package com.rajtechnologies.springbootdatajpa.model;

import com.rajtechnologies.springbootdatajpa.entity.Customer;
import lombok.*;

import java.util.List;

@Builder
@Setter
@Getter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomersRequest {
    private List<Customer> customers;
}
