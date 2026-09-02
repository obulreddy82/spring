package com.rajtechnologies.springbootdatajpa.service;

import com.rajtechnologies.springbootdatajpa.entity.User;
import com.rajtechnologies.springbootdatajpa.model.UserRequest;
import com.rajtechnologies.springbootdatajpa.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public List<User> createUsers(UserRequest userRequest) {
        userRepository.saveAll(userRequest.getUsers());
        return userRequest.getUsers();
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUser(Long id) {
        return userRepository.findById(id);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public List<User> getUsersByNameAndEmail(String name, String Email) {
        return userRepository.findByNameAndEmail(name, Email);
    }

    public List<User> getUsersByEmail(String email) {
        Sort sort = Sort.by(Sort.Direction.DESC, "name");
        return userRepository.findByEmail(email, sort);
    }

    public List<User> getUsers(int page, int limit) {

        Pageable pageable = PageRequest.of(page, limit);//pageNumber, pageSize
        Page<User> users = userRepository.findAll(pageable);
        System.out.println("Total Elements:" + users.getTotalElements());
        System.out.println("Total Pages:" + users.getTotalPages());
        System.out.println("Get Number:" + users.getNumber());
        System.out.println("Page size:" + users.getSize());
        System.out.println("Has Content:" + users.hasContent());
        System.out.println("Is First:" + users.isFirst());
        System.out.println("Is Last:" + users.isLast());
        System.out.println("Has Next:" + users.hasNext());
        System.out.println("Has Previous:" + users.hasPrevious());
        System.out.println("Reference:" + users.get().toList());

        return users.getContent();
    }

    public List<User> getAdultUsers(int age) {
        return userRepository.getAdultUsers(age);
    }


}
