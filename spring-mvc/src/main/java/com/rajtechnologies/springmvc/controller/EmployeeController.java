package com.rajtechnologies.springmvc.controller;

import com.rajtechnologies.springmvc.model.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @GetMapping("/details")
    public String employeeDetails(Model model){
        Employee employee = new Employee();
        employee.setName("Obul");
        employee.setDesignation("Software Engineer");
        employee.setAge("44");
        employee.setSex("Male");
        model.addAttribute("employee", employee);
        return "employee";
    }
}
