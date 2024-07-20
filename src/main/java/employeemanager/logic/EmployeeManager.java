package employeemanager.logic;

import employeemanager.domain.Employee;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class EmployeeManager {
    private final Map<String, Employee> employees;

    public EmployeeManager() {
        employees = new LinkedHashMap<>();
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

    public boolean hasEmployee(String name) {
        return employees.containsKey(name);
    }

    public boolean remove(String name) {
        if (employees.containsKey(name)) {
            employees.remove(name);
            return true;
        }

        return false;
    }

    public boolean save(String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            for (Employee employee : employees.values()) {
                String employeeData = employee.getName() + "," + employee.getJobTitle() + "," + employee.getSalary();

                writer.write(employeeData);
                writer.write(System.lineSeparator());
            }

            return true;
        } catch (IOException e) {
            return false;
        }
    }
}
