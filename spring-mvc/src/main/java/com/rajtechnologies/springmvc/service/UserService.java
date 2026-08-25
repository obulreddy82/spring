package com.rajtechnologies.springmvc.service;

import com.rajtechnologies.springmvc.dto.LoginDto;
import com.rajtechnologies.springmvc.dto.UserRegistrationDto;
import com.rajtechnologies.springmvc.exception.InvalidCredentialsException;
import com.rajtechnologies.springmvc.exception.UserAlreadyExistsException;
import com.rajtechnologies.springmvc.exception.UserNotFoundException;
import com.rajtechnologies.springmvc.model.User;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class UserService {

    private final Map<String, User> usersByUsername = new ConcurrentHashMap<>();
    private final Map<String, User> usersByEmail = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    public User register(UserRegistrationDto registrationDto) {
        if (usersByUsername.containsKey(registrationDto.getUsername().toLowerCase())) {
            throw new UserAlreadyExistsException("Username is already taken");
        }
        if (usersByEmail.containsKey(registrationDto.getEmail().toLowerCase())) {
            throw new UserAlreadyExistsException("Email is already registered");
        }

        User user = new User(
                idSequence.getAndIncrement(),
                registrationDto.getUsername(),
                registrationDto.getEmail(),
                registrationDto.getPassword(),
                registrationDto.getFullName()
        );

        usersByUsername.put(user.getUsername().toLowerCase(), user);
        usersByEmail.put(user.getEmail().toLowerCase(), user);
        return user;
    }

    public User authenticate(LoginDto loginDto) {
        User user = usersByUsername.get(loginDto.getUsername().toLowerCase());
        if (user == null || !user.getPassword().equals(loginDto.getPassword())) {
            throw new InvalidCredentialsException("Invalid username or password");
        }
        return user;
    }

    public User findByUsername(String username) {
        User user = usersByUsername.get(username.toLowerCase());
        if (user == null) {
            throw new UserNotFoundException("User not found: " + username);
        }
        return user;
    }
}
