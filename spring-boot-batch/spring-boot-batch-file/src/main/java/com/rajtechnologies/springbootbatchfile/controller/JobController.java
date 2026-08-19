package com.rajtechnologies.springbootbatchfile.controller;


import org.springframework.batch.core.launch.JobOperator;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Properties;

@RestController
public class JobController {

    private final JobOperator jobOperator;


    public JobController(JobOperator jobOperator) {
        this.jobOperator = jobOperator;
    }

    @PostMapping("/batch/customers")
    public String runCustomerJob() throws Exception {
        Properties parameters = new Properties();
        parameters.setProperty("timestamp", String.valueOf(System.currentTimeMillis()));
        parameters.setProperty("fileName", "customers_dynamic.json");

        jobOperator.start("customerJob", parameters);
        return "Customer job started";
    }

    @PostMapping("/batch/employees")
    public String runEmployeeJob() throws Exception {
        Properties parameters = new Properties();
        parameters.setProperty("timestamp", String.valueOf(System.currentTimeMillis()));


        jobOperator.start("employeeJob", parameters);
        return "Employee job started";
    }

}
