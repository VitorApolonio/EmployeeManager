package employeemanager.logic;

import employeemanager.domain.Employee;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

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

    public boolean load(String filename) {
        List<Employee> readEmployees = new ArrayList<>();

        try (Scanner scanner = new Scanner(Path.of(filename))) {
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                String[] employeeData = row.split(",");

                Employee employee = new Employee(employeeData[0], employeeData[1], new BigDecimal(employeeData[2]));

                readEmployees.add(employee);
            }

            readEmployees.stream().forEach(e -> employees.put(e.getName(), e));

            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
