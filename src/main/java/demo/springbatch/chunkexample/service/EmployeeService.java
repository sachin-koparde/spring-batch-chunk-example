package demo.springbatch.chunkexample.service;

import demo.springbatch.chunkexample.model.Employee;
import demo.springbatch.chunkexample.repositoy.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;

    public void saveAll(List<? extends Employee> employees) {

        employeeRepository.saveAll(employees);

    }

}
