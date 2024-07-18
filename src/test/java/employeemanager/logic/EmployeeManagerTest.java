package employeemanager.logic;

import employeemanager.domain.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeManagerTest {

    @Test
    void getEmployees_returns_employee_list() {
        EmployeeManager manager = new EmployeeManager();
        Assertions.assertInstanceOf(Map.class, manager.getEmployees());
    }

    @Test
    void add_updates_employee_list() {
        Employee employee = new Employee("John Doe", "HR Manager", new BigDecimal("8000.00"));
        EmployeeManager manager = new EmployeeManager();

        manager.add(employee);

        Assertions.assertTrue(manager.getEmployees().containsValue(employee));
    }

    @Test
    void findByName_returns_correct_employee() {
        EmployeeManager manager = new EmployeeManager();
        Employee john = new Employee("John Doe", "HR Manager", new BigDecimal("8000.00"));
        Employee alice = new Employee("Alice", "HR Supervisor", new BigDecimal("6000.00"));
        Employee bob = new Employee("Bob", "Sales Manager", new BigDecimal("12000.00"));

        manager.add(john);
        manager.add(alice);
        manager.add(bob);

        Assertions.assertEquals(alice, manager.findByName("Alice"));
        Assertions.assertEquals(bob, manager.findByName("Bob"));
        Assertions.assertNotEquals(bob, manager.findByName("John Doe"));
    }

    @Test
    void remove_updates_employee_list() {
        EmployeeManager manager = new EmployeeManager();
        Employee john = new Employee("John Doe", "HR Manager", new BigDecimal("8000.00"));
        Employee alice = new Employee("Alice", "HR Supervisor", new BigDecimal("6000.00"));
        Employee bob = new Employee("Bob", "Sales Manager", new BigDecimal("12000.00"));

        manager.add(john);
        manager.add(alice);
        manager.add(bob);

        manager.remove("Bob");

        Assertions.assertNull(manager.findByName("Bob"));
    }
}