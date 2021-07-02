package demo.springbatch.chunkexample.controller;

import demo.springbatch.chunkexample.model.Employee;
import demo.springbatch.chunkexample.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
@Component
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;


    public void saveAll(List<? extends Employee> employees) {
        employeeService.saveAll(employees);
    }

}
