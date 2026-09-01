package com.rajtechnologies.springmvc.service;

import com.rajtechnologies.springmvc.model.Employee;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class EmployeeService {

    Map<String, Employee> employeeMap = new ConcurrentHashMap<>();

    public void saveEmployee(Employee employee) {
        employeeMap.put(employee.getName(), employee);
    }

    public List<Employee> getAllEmployees() {

        return employeeMap.values().stream().toList();

    }

    public Employee getEmployee(String name) {

        return employeeMap.getOrDefault(name, new Employee());

    }

    public String storeData(MultipartFile file) throws IOException {

        //excel
        InputStream inputStream = file.getInputStream();
        Workbook workbook = WorkbookFactory.create(inputStream);
        workbook.getSheetAt(0).forEach(row -> {
            System.out.println(row.getCell(0).getNumericCellValue() +
                    " " + row.getCell(1).getStringCellValue());
        });

//        //CSV
//        Reader reader = new InputStreamReader(file.getInputStream(),
//                StandardCharsets.UTF_8);
//        CSVParser csvParser= CSVFormat.DEFAULT
//                .builder().setHeader()
//                .setSkipHeaderRecord(true)
//                .build()
//                .parse(reader);
//        csvParser.forEach(record -> {
//            System.out.println(record.toMap());
//        });
        return "File uploaded successfully!";
    }
}
