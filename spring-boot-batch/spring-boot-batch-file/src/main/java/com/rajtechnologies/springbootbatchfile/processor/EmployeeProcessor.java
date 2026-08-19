package com.rajtechnologies.springbootbatchfile.processor;

import com.rajtechnologies.springbootbatchfile.model.Employee;
import org.jspecify.annotations.Nullable;
import org.springframework.batch.infrastructure.item.ItemProcessor;

public class EmployeeProcessor implements ItemProcessor<Employee, Employee> {
    @Override
    public @Nullable Employee process(Employee employee) throws Exception {
        if(employee.getEmail() == null || employee.getEmail().isBlank())   {
            throw new IllegalArgumentException("employee email is invalid"+employee.getEmail());
        }
       if(employee.getAge() < 18)   {
           throw new IllegalArgumentException("employee age is invalid"+employee.getAge());
       }
        return employee;
    }
}
