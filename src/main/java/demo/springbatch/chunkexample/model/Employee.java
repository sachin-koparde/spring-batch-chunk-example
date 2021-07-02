package demo.springbatch.chunkexample.model;

import javax.persistence.*;

@Entity
@Table(name = "employees")
public class Employee {

    @Id
    private Long employeeId;

    @Column
    private String employeeName;

    @Column
    private String employeeDesignation;

    public Employee() {
    }

    public Employee(String employeeName, String employeeDesignation) {
        this.employeeName = employeeName;
        this.employeeDesignation = employeeDesignation;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getEmployeeDesignation() {
        return employeeDesignation;
    }

    public void setEmployeeDesignation(String employeeDesignation) {
        this.employeeDesignation = employeeDesignation;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", employeeName='" + employeeName + '\'' +
                ", employeeDesignation='" + employeeDesignation + '\'' +
                '}';
    }
}
