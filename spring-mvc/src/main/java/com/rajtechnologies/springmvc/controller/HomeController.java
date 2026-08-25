package com.rajtechnologies.springmvc.controller;

import com.rajtechnologies.springmvc.model.User;
import com.rajtechnologies.springmvc.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final UserService userService;

    public HomeController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/home")
    public String home(HttpSession session, Model model) {
        String username = (String) session.getAttribute(AuthController.SESSION_USER);
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "home";
    }
}
