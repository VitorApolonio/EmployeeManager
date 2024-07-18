package employeemanager.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class EmployeeTest {

    @Test
    void getName_returns_employee_name() {
        Employee employee = new Employee("John Doe", "HR Manager", new BigDecimal("5000.00"));
        Assertions.assertEquals("John Doe", employee.getName());
    }

    @Test
    void getJobTitle_returns_employee_job_title() {
        Employee employee = new Employee("John Doe", "HR Manager", new BigDecimal("5000.00"));
        Assertions.assertEquals("HR Manager", employee.getJobTitle());
    }

    @Test
    void setJobTitle_updates_employee_job_title() {
        Employee employee = new Employee("John Doe", "HR Manager", new BigDecimal("5000.00"));
        employee.setJobTitle("Chief Executive Officer");
        Assertions.assertEquals("Chief Executive Officer", employee.getJobTitle());
    }

    @Test
    void getSalary_returns_employee_salary() {
        Employee employee = new Employee("John Doe", "HR Manager", new BigDecimal("5000.00"));
        BigDecimal expectedSalary = new BigDecimal("5000.00");
        Assertions.assertEquals(expectedSalary, employee.getSalary());
    }

    @Test
    void setSalary_updates_employee_salary() {
        Employee employee = new Employee("John Doe", "HR Manager", new BigDecimal("5000.00"));
        BigDecimal newSalary = new BigDecimal("8000.00");
        employee.setSalary(newSalary);
        Assertions.assertEquals(newSalary, employee.getSalary());
    }
}