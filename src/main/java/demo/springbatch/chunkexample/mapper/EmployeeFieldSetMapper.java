package demo.springbatch.chunkexample.mapper;

import demo.springbatch.chunkexample.model.Employee;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
public class EmployeeFieldSetMapper  implements FieldSetMapper<Employee> {
    @Override
    public Employee mapFieldSet(FieldSet fieldSet) throws BindException {
        final Employee employee = new Employee();

        employee.setEmployeeId(fieldSet.readLong(0));
        employee.setEmployeeName(fieldSet.readString(1));
        employee.setEmployeeDesignation(fieldSet.readString(2));

        return employee;
    }
}
