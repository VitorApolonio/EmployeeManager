package employeemanager.logic;

import employeemanager.domain.Employee;

import java.util.HashMap;
import java.util.Map;

public class EmployeeManager {
    private final Map<String, Employee> employees;

    public EmployeeManager() {
        employees = new HashMap<>();
    }

    public void add(Employee employee) {
        employees.put(employee.getName(), employee);
    }

    public Map<String, Employee> getEmployees() {
        return employees;
    }

    public Employee findByName(String name) {
        return employees.get(name);
    }

    public boolean remove(String name) {
        if (employees.containsKey(name)) {
            employees.remove(name);
            return true;
        }

        return false;
    }
}
