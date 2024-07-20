package employeemanager.ui;

import employeemanager.logic.EmployeeManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;

class TextInterfaceTest {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    PrintStream originalOut = System.out;
    String menu = """
            *******************************
            * Gerenciador de Funcionários *
            *******************************
            
            [1] Ver lista de funcionários
            [2] Encontrar funcionário por nome
            [3] Adicionar funcionário a lista
            [4] Remover funcionário da lista
            [x] Sair
            
            > \
            """;

    @BeforeEach
    void setUp() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    void tearDown() {
        System.setOut(originalOut);
    }

    @Test
    void start_prints_menu_correctly() {
        Scanner scanner = new Scanner("x");
        EmployeeManager manager = new EmployeeManager();
        TextInterface text = new TextInterface(scanner, manager);

        text.start();

        String expected = fixNewLine(menu + "Saindo...\n");

        Assertions.assertEquals(expected, outContent.toString());
    }

    @Test
    void start_add_employee_prints_correctly() {
        String inputs = """
                3
                John Doe
                HR Manager
                1500.00
                x
                """;

        Scanner scanner = new Scanner(inputs);
        EmployeeManager manager = new EmployeeManager();
        TextInterface text = new TextInterface(scanner, manager);

        String expected = fixNewLine(menu + """
                Nome do funcionário: \
                Cargo do funcionário: \
                Salário mensal: \
                Funcionário adicionado com sucesso.
                
                """ +
                menu +
                "Saindo...\n");

        text.start();

        Assertions.assertEquals(expected, outContent.toString());
    }

    @Test
    void start_employee_list_prints_correctly() {
        String inputs = """
                3
                John Doe
                HR Manager
                1500.00
                3
                John Smith
                HR Supervisor
                5000.00
                1
                
                x
                """;

        Scanner scanner = new Scanner(inputs);
        EmployeeManager manager = new EmployeeManager();
        TextInterface text = new TextInterface(scanner, manager);

        // \u00A0 is the unicode escape sequence for the non-breaking space (nbsp)
        String expected = fixNewLine(menu + """
                Nome do funcionário: \
                Cargo do funcionário: \
                Salário mensal: \
                Funcionário adicionado com sucesso.
                
                """ +
                menu +
                """
                Nome do funcionário: \
                Cargo do funcionário: \
                Salário mensal: \
                Funcionário adicionado com sucesso.
                
                """ +
                menu +
                """
                John Smith, HR Supervisor, R$\u00A05.000,00
                John Doe, HR Manager, R$\u00A01.500,00
                
                A empresa possui 2 funcionários.
                [Continuar]
                """ +
                menu +
                "Saindo...\n");

        text.start();

        Assertions.assertEquals(expected, outContent.toString());
    }

    @Test
    void remove_employee_updates_employee_list() {
        String inputs = """
                3
                John Doe
                HR Manager
                1500.00
                3
                John Smith
                HR Supervisor
                5000.00
                4
                John Smith
                s
                x
                """;

        Scanner scanner = new Scanner(inputs);
        EmployeeManager manager = new EmployeeManager();
        TextInterface text = new TextInterface(scanner, manager);

        text.start();

        Assertions.assertFalse(manager.getEmployees().containsKey("John Smith"));
    }

    @Test
    void remove_employee_prints_correctly() {
        String inputs = """
                3
                John Doe
                HR Manager
                1500.00
                3
                John Smith
                HR Supervisor
                5000.00
                4
                John Smith
                s
                x
                """;

        Scanner scanner = new Scanner(inputs);
        EmployeeManager manager = new EmployeeManager();
        TextInterface text = new TextInterface(scanner, manager);

        text.start();

        String expected = fixNewLine(menu + """
                Nome do funcionário: \
                Cargo do funcionário: \
                Salário mensal: \
                Funcionário adicionado com sucesso.
                
                """ +
                menu +
                """
                Nome do funcionário: \
                Cargo do funcionário: \
                Salário mensal: \
                Funcionário adicionado com sucesso.
                
                """ +
                menu +
                """
                Nome do funcionário: \
                Tem certeza que deseja remover o funcionário John Smith do sistema? (s/N)\s
                > \
                Funcionário removido com sucesso.
                
                """ +
                menu +
                "Saindo...\n");

        Assertions.assertEquals(expected, outContent.toString());
    }

    // Used to replace \n with the system's line separator on strings
    private String fixNewLine(String string) {
        return string.replaceAll("\n", System.lineSeparator());
    }
}