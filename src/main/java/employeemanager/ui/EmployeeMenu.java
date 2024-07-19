package employeemanager.ui;

import employeemanager.domain.Employee;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Scanner;

public class EmployeeMenu {
    private final Scanner scanner;
    private final Employee employee;

    public EmployeeMenu(Scanner scanner, Employee employee) {
        this.scanner = scanner;
        this.employee = employee;
    }

    public void start() {
        menuLoop: while (true) {
            printMenu();
            System.out.print("> ");
            String choice = scanner.nextLine().strip();

            switch (choice) {
                case "1":
                    printEmployeeInfo();
                    break;
                case "2":
                    updateJobTitle();
                    break;
                case "3":
                    updateSalary();
                    break;
                case "x":
                case "X":
                    break menuLoop;
                default:
                    System.out.println("Opção inválida.");
                    System.out.println();
            }
        }
    }

    private void printMenu() {
        System.out.println(employee.getName() + " - Funcionário");
        System.out.println();
        System.out.println("[1] Ver informações sobre funcionário");
        System.out.println("[2] Alterar cargo do funcionário");
        System.out.println("[3] Alterar salário do funcionário");
        System.out.println("[x] Voltar");
        System.out.println();
    }

    private void printEmployeeInfo() {
        // Used to format salary into local currency
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

        System.out.println("Nome: " + employee.getName());
        System.out.println("Cargo: " + employee.getJobTitle());
        System.out.println("Salário: " + currencyFormat.format(employee.getSalary()));
        System.out.print("[Continuar]");
        scanner.nextLine();

        System.out.println();
    }

    private void updateSalary() {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();

        BigDecimal salary;

        while (true) {
            try {
                System.out.println("Salário atual: " + currencyFormat.format(employee.getSalary()));
                System.out.print("Novo salário (Enter para não alterar): ");
                String input = scanner.nextLine().strip();

                if (input.isBlank()) {
                    System.out.println("Salário não alterado.");
                    break;
                }

                salary = new BigDecimal(input);
                // Adjust to 2 decimal places
                salary = salary.setScale(2, RoundingMode.DOWN);
                employee.setSalary(salary);

                System.out.println("Salário atualizado com sucesso.");
                break;
            } catch (NumberFormatException e) {
                System.out.println("Número inválido (obs. use um ponto como separador decimal).");
                System.out.println();
            }
        }

        System.out.println();
    }

    private void updateJobTitle() {
        System.out.println("Cargo atual: " + employee.getJobTitle());
        System.out.print("Novo cargo (Enter para não alterar): ");
        String jobTitle = scanner.nextLine().strip();

        if (jobTitle.trim().isBlank()) {
            System.out.println("Cargo não alterado.");
        } else {
            employee.setJobTitle(jobTitle);

            System.out.println("Cargo atualizado com sucesso.");
        }

        System.out.println();
    }
}
