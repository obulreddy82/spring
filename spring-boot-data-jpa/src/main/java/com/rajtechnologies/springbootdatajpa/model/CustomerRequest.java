package com.rajtechnologies.springbootdatajpa.model;

import com.rajtechnologies.springbootdatajpa.entity.Customer;
import lombok.*;
import org.springframework.stereotype.Service;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CustomerRequest {
    private Customer customer;
}
