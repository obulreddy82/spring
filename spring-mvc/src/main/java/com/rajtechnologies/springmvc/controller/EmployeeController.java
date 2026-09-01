package com.rajtechnologies.springmvc.controller;

import com.rajtechnologies.springmvc.model.Employee;
import com.rajtechnologies.springmvc.service.EmployeeService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/upload/form")
    public String fileUpload(Model model) {
        System.out.println(" file upload form will be displayed");
        return "upload";
    }
    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String s = employeeService.storeData(file);
        System.out.println(s);
        return "employee";
    }

    @GetMapping("/details")
    public String employeeDetails(Model model, HttpSession session) {
        String username = (String) session.getAttribute(AuthController.SESSION_USER);
        if (username == null) {
            return "redirect:/login";
        }
//        Employee employee = new Employee();
//        employee.setName("Obul");
//        employee.setDesignation("Software Engineer");
//        employee.setAge("44");
//        employee.setSex("Male");
        List<Employee> allEmployees = employeeService.getAllEmployees();
        model.addAttribute("employees", allEmployees);
        return "employee";
    }

    @GetMapping("/form")
    public String employeeForm(Model model) {

        if (!model.containsAttribute("employee")) {
            model.addAttribute("employee", new Employee());
        }
        return "employee_save";
    }

    @PostMapping("/save")
    public String saveEmployee(
            @ModelAttribute("employee") Employee employee,
            BindingResult bindingResult,
            Model model,
            RedirectAttributes redirectAttributes) {
        if(bindingResult.hasErrors()) {
            return "employee_save";
        }
        if (!model.containsAttribute("employee")) {
            model.addAttribute("employee", new Employee());
        }
        employeeService.saveEmployee(employee);
        redirectAttributes.addFlashAttribute("successMessage",
                "Employee saved successfully!");
        return "redirect:/employee/details";
    }
}
