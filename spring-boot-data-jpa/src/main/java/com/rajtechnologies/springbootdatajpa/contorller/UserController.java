package com.rajtechnologies.springbootdatajpa.contorller;

import com.rajtechnologies.springbootdatajpa.entity.Customer;
import com.rajtechnologies.springbootdatajpa.entity.User;
import com.rajtechnologies.springbootdatajpa.model.CustomerRequest;
import com.rajtechnologies.springbootdatajpa.model.CustomersRequest;
import com.rajtechnologies.springbootdatajpa.model.UserRequest;
import com.rajtechnologies.springbootdatajpa.service.CustomerService;
import com.rajtechnologies.springbootdatajpa.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final CustomerService customerService;

    public UserController(UserService userService, CustomerService customerService) {
        this.userService = userService;
        this.customerService = customerService;
    }

    @GetMapping
    public List<User> getUsers() {
        return userService.getUsers();
    }

//    @GetMapping("/{id}")
//    public Optional<User> getUser(@PathVariable Long id) {
//        return userService.getUser(id);
//    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PostMapping("/create")
    public List<User> createUsers(@RequestBody UserRequest user) {
        return userService.createUsers(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{name}/{email}")
    public List<User> getUsersByNameAndEmail(@PathVariable String name, @PathVariable String email) {
        return userService.getUsersByNameAndEmail(name, email);
    }

    @GetMapping("/{email}")
    public List<User> getUsersByEmail(@PathVariable String email) {
        return userService.getUsersByEmail(email);
    }

    @GetMapping("/limit")
    public List<User> getUsersBasedOnnPageLimit(@RequestParam int page, @RequestParam int limit) {
        return userService.getUsers(page, limit);
    }

    @GetMapping("/adults")
    public List<User> getAdultUsers(@RequestParam int age) {
        return userService.getAdultUsers(age);
    }

    //Customer and Order relation
    @PostMapping("/create/customer")
    public Customer createCustomer(@RequestBody CustomerRequest customerRequest) {
        return customerService.saveCustomer(customerRequest);
    }

    @PostMapping("/create/customers")
    public List<Customer> createCustomers(@RequestBody CustomersRequest customersRequest) {
        return customerService.saveCustomers(customersRequest);
    }

    @GetMapping("/customers")
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @PutMapping("/customer/{id}")
    public void updateCustomer(@PathVariable Long id, @RequestBody Customer customer) {
         customerService.updateCustomer(id, customer);

    }
}
