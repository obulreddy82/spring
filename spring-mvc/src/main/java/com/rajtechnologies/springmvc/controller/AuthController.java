package com.rajtechnologies.springmvc.controller;

import com.rajtechnologies.springmvc.dto.LoginDto;
import com.rajtechnologies.springmvc.dto.UserRegistrationDto;
import com.rajtechnologies.springmvc.exception.InvalidCredentialsException;
import com.rajtechnologies.springmvc.exception.UserAlreadyExistsException;
import com.rajtechnologies.springmvc.model.User;
import com.rajtechnologies.springmvc.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class AuthController {

    public static final String SESSION_USER = "loggedInUser";

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        if (!model.containsAttribute("userRegistrationDto")) {
            model.addAttribute("userRegistrationDto", new UserRegistrationDto());
        }
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@Valid @ModelAttribute("userRegistrationDto") UserRegistrationDto userRegistrationDto,
                               BindingResult bindingResult,
                               Model model,
                               RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "register";
        }

        try {
            userService.register(userRegistrationDto);
        } catch (UserAlreadyExistsException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            return "register";
        }

        redirectAttributes.addFlashAttribute("successMessage",
                "Registration successful! Please log in with your credentials.");
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model,
                                @RequestParam(value = "error", required = false) String error) {
        if (!model.containsAttribute("loginDto")) {
            model.addAttribute("loginDto", new LoginDto());
        }
        if (error != null) {
            model.addAttribute("errorMessage", "Your session has expired. Please log in again.");
        }
        return "login";
    }

    @PostMapping("/login")
    public String loginUser(@Valid @ModelAttribute("loginDto") LoginDto loginDto,
                            BindingResult bindingResult,
                            HttpSession session,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            return "login";
        }

        try {
            User user = userService.authenticate(loginDto);
            session.setAttribute(SESSION_USER, user.getUsername());
            redirectAttributes.addFlashAttribute("successMessage", "Welcome back, " + user.getFullName() + "!");
            return "redirect:/home";
        } catch (InvalidCredentialsException ex) {
            model.addAttribute("errorMessage", ex.getMessage());
            LoginDto preservedLogin = new LoginDto();
            preservedLogin.setUsername(loginDto.getUsername());
            model.addAttribute("loginDto", preservedLogin);
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, RedirectAttributes redirectAttributes) {
        session.invalidate();
        redirectAttributes.addFlashAttribute("successMessage", "You have been logged out successfully.");
        return "redirect:/login";
    }
}
