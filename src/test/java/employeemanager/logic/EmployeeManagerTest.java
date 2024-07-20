package employeemanager.logic;

import employeemanager.domain.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.Random;

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

    @Test
    void hasEmployee_returns_correctly() {
        EmployeeManager manager = new EmployeeManager();
        Employee john = new Employee("John Doe", "HR Manager", new BigDecimal("8000.00"));

        manager.add(john);

        Assertions.assertTrue(manager.hasEmployee("John Doe"));
        Assertions.assertFalse(manager.hasEmployee("John Smith"));
    }

    @Test
    void save_writes_to_file() throws IOException {
        EmployeeManager manager = new EmployeeManager();
        Employee john = new Employee("John Doe", "HR Manager", new BigDecimal("8000.00"));
        Employee alice = new Employee("Alice", "HR Supervisor", new BigDecimal("6000.00"));
        Employee bob = new Employee("Bob", "Sales Manager", new BigDecimal("12000.00"));

        manager.add(john);
        manager.add(alice);
        manager.add(bob);

        Random rand = new Random();
        String fileName = "_savetest_" + rand.nextInt(800) + ".txt";

        manager.save(fileName);

        String expected = """
                John Doe,HR Manager,8000.00
                Alice,HR Supervisor,6000.00
                Bob,Sales Manager,12000.00
                """.replaceAll("\n", System.lineSeparator());

        String fileContent = Files.readString(Path.of(fileName));

        Assertions.assertEquals(expected, fileContent);

        Files.deleteIfExists(Path.of(fileName));
    }
}