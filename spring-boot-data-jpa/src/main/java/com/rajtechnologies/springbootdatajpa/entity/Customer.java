package com.rajtechnologies.springbootdatajpa.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Builder
@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Version
    private Long version;

    @OneToMany(mappedBy = "customer",
            cascade = CascadeType.ALL,
            // fetch = FetchType.EAGER,
            orphanRemoval = true)
    @JsonManagedReference
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    //@BatchSize(size = 10)
    private List<Order> orders = new ArrayList<>();
}
