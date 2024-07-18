package employeemanager;

import java.math.BigDecimal;

public class Employee {
    private final String name;
    private String jobTitle;
    private BigDecimal salary;

    public Employee(String name, String jobTitle, String salary) {
        this.name = name;
        this.jobTitle = jobTitle;
        this.salary = new BigDecimal(salary);
    }

    public String getName() {
        return name;
    }

    
}
