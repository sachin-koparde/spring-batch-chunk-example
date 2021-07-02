package demo.springbatch.chunkexample.processor;

import demo.springbatch.chunkexample.model.Employee;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class EmployeeProcessor implements ItemProcessor<Employee, Employee> {

    private final Logger log = Logger.getLogger(getClass().getName());



    @Override
    public Employee process(Employee employee) throws Exception {

        Employee processedEmployee = null;

        final String DESIGNATION = "Manager";

        String designation = employee.getEmployeeDesignation();

        if (designation.equals(DESIGNATION)) {
            processedEmployee = new Employee();
            processedEmployee.setEmployeeId(employee.getEmployeeId());
            processedEmployee.setEmployeeName(employee.getEmployeeName());
            processedEmployee.setEmployeeDesignation(employee.getEmployeeDesignation());

        }

        return processedEmployee;

    }
}
