package employeemanager.domain;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Objects;

public class Employee {
    private final String name;
    private String jobTitle;
    private BigDecimal salary;

    public Employee(String name, String jobTitle, BigDecimal salary) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.salary = salary;
    }

    public String getName() {
        return name;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public BigDecimal getSalary() {
        return salary;
    }

    public void setSalary(BigDecimal salary) {
        this.salary = salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Employee employee)) return false;
        return Objects.equals(name, employee.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
        String formattedSalary = currencyFormat.format(salary);

        return name + ", " + jobTitle + ", " + formattedSalary;
    }
}
