package demo.springbatch.chunkexample.writer;

import demo.springbatch.chunkexample.controller.EmployeeController;
import demo.springbatch.chunkexample.model.Employee;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.logging.Logger;

@Component
public class EmployeeWriter implements ItemWriter<Employee> {

    @Autowired
    private EmployeeController employeeController;

    private final Logger log = Logger.getLogger(getClass().getName());


    @Override
    public void write(List<? extends Employee> employeeList) throws Exception {

        employeeController.saveAll(employeeList);

    }

}
