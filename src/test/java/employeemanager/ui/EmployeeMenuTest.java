package employeemanager.ui;

import employeemanager.domain.Employee;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeMenuTest {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    Employee testEmployee = new Employee("John Smith", "HR Manager", new BigDecimal("3000.00"));
    String title = "* " + testEmployee.getName() + " - Funcionário *";
    String menu = "*".repeat(title.length()) +
            "\n" +
            title +
            "\n" +
            "*".repeat(title.length()) + """
            
            
            [1] Ver informações sobre funcionário
            [2] Alterar cargo do funcionário
            [3] Alterar salário do funcionário
            [x] Voltar
            
            > \
            """;
    String continueButtonText = "[Aperte Enter para continuar]\n";

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void menu_prints_correctly() {
        String input = "x";

        Scanner scanner = new Scanner(input);
        EmployeeMenu employeeMenu = new EmployeeMenu(scanner, testEmployee);

        String expected = fixNewLine(menu);

        employeeMenu.start();

        Assertions.assertEquals(expected, outContent.toString());
    }

    @Test
    void employee_info_prints_correctly() {
        String input = """
                1
                
                x
                """;

        Scanner scanner = new Scanner(input);
        EmployeeMenu employeeMenu = new EmployeeMenu(scanner, testEmployee);

        String expected = fixNewLine(menu +
                """
                Nome: John Smith
                Cargo: HR Manager
                Salário: R$\u00A03.000,00
                """ +
                continueButtonText +
                menu);

        employeeMenu.start();

        Assertions.assertEquals(expected, outContent.toString());
    }

    @Test
    void change_job_title_prints_correctly() {
        String input = """
                2
                Chief Executive Officer
                
                x
                """;

        Scanner scanner = new Scanner(input);
        EmployeeMenu employeeMenu = new EmployeeMenu(scanner, testEmployee);

        String expected = fixNewLine(menu +
                """
                Cargo atual: HR Manager
                Novo cargo (Enter para não alterar): \
                
                Cargo atualizado com sucesso.
                """ +
                continueButtonText +
                menu);

        employeeMenu.start();

        Assertions.assertEquals(expected, outContent.toString());
    }

    @Test
    void change_job_title_updates_employee_job_title() {
        String input = """
                2
                Chief Executive Officer
                
                x
                """;

        Scanner scanner = new Scanner(input);
        EmployeeMenu employeeMenu = new EmployeeMenu(scanner, testEmployee);

        employeeMenu.start();

        Assertions.assertEquals("Chief Executive Officer", testEmployee.getJobTitle());
    }

    @Test
    void change_salary_prints_correctly() {
        String input = """
                3
                12000
                
                x
                """;

        Scanner scanner = new Scanner(input);
        EmployeeMenu employeeMenu = new EmployeeMenu(scanner, testEmployee);

        String expected = fixNewLine(menu +
                """
                Salário atual: R$\u00A03.000,00
                Novo salário (Enter para não alterar): \
                
                Salário atualizado com sucesso.
                """ +
                continueButtonText +
                menu);

        employeeMenu.start();

        Assertions.assertEquals(expected, outContent.toString());
    }

    @Test
    void change_salary_updates_employee_salary() {
        String input = """
                3
                12000
                
                x
                """;

        Scanner scanner = new Scanner(input);
        EmployeeMenu employeeMenu = new EmployeeMenu(scanner, testEmployee);

        employeeMenu.start();

        Assertions.assertEquals("12000.00", testEmployee.getSalary().toString());
    }

    // Used to replace \n with the system's line separator on strings
    private String fixNewLine(String string) {
        return string.replaceAll("\n", System.lineSeparator());
    }
}