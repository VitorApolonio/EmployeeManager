package employeemanager.ui;

import employeemanager.domain.Employee;
import employeemanager.logic.EmployeeManager;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Scanner;

public class TextInterface {
    private final Scanner scanner;
    private final EmployeeManager manager;

    public TextInterface(Scanner scanner, EmployeeManager manager) {
        this.scanner = scanner;
        this.manager = manager;
    }

    public void start() {
        mainLoop: while (true) {
            printMenu();
            System.out.print("> ");
            String choice = scanner.nextLine().strip();

            switch (choice) {
                case "1":
                    printEmployeeList();
                    break;
                case "2":
                    findEmployee();
                    break;
                case "3":
                    addEmployee();
                    break;
                case "4":
                    removeEmployee();
                    break;
                case "x":
                case "X":
                    System.out.println("Saindo...");
                    break mainLoop;
                default:
                    System.out.println("Opção inválida.");
                    System.out.println();
            }
        }
    }

    private void printMenu() {
        System.out.println("*******************************");
        System.out.println("* Gerenciador de Funcionários *");
        System.out.println("*******************************");
        System.out.println();
        System.out.println("[1] Ver lista de funcionários");
        System.out.println("[2] Encontrar funcionário por nome");
        System.out.println("[3] Adicionar funcionário a lista");
        System.out.println("[4] Remover funcionário da lista");
        System.out.println("[x] Sair");
        System.out.println();
    }

    private void printEmployeeList() {
        if (manager.getEmployees().isEmpty()) {
            System.out.println("A empresa não possui funcionários.");
        } else {
            for (Employee employee : manager.getEmployees().values()) {
                System.out.println(employee);
            }

            System.out.println();

            if (manager.getEmployees().size() == 1) {
                System.out.println("A empresa possui 1 funcionário.");
            } else {
                System.out.println("A empresa possui " + manager.getEmployees().size() + " funcionários.");
            }

            System.out.print("[Continuar]");
            scanner.nextLine();
        }

        System.out.println();
    }

    private void findEmployee() {
        if (manager.getEmployees().isEmpty()) {
            System.out.println("Não há funcionários no sistema.");
            System.out.println();
            return;
        }

        System.out.print("Nome do funcionário: ");
        String name = scanner.nextLine().strip();
        System.out.println();

        if (manager.getEmployees().containsKey(name)) {
            Employee employee = manager.findByName(name);
            EmployeeMenu employeeMenu = new EmployeeMenu(scanner, employee);

            employeeMenu.start();
        } else {
            System.out.println("Funcionário " + name + " não encontrado.");
        }

        System.out.println();
    }

    private void addEmployee() {
        System.out.print("Nome do funcionário: ");
        String name = scanner.nextLine().strip();
        System.out.print("Cargo do funcionário: ");
        String jobTitle = scanner.nextLine().strip();

        BigDecimal salary;

        while (true) {
            try {
                System.out.print("Salário mensal: ");
                salary = new BigDecimal(scanner.nextLine().strip());
                // Adjust to 2 decimal places
                salary = salary.setScale(2, RoundingMode.DOWN);
                break;
            } catch (NumberFormatException e) {
                System.out.println("Número inválido (obs. use um ponto como separador decimal).");
                System.out.println();
            }
        }

        Employee newEmployee = new Employee(name, jobTitle, salary);

        manager.add(newEmployee);

        System.out.println("Funcionário adicionado com sucesso.");
        System.out.println();
    }

    private void removeEmployee() {
        if (manager.getEmployees().isEmpty()) {
            System.out.println("A empresa não possui funcionários.");
        } else {
            System.out.print("Nome do funcionário: ");
            String name = scanner.nextLine().strip();

            if (manager.getEmployees().containsKey(name)) {
                System.out.println("Tem certeza que deseja remover o funcionário " + name + " do sistema? (s/N) ");
                System.out.print("> ");
                String confirmation = scanner.nextLine().strip();

                if ("s".equalsIgnoreCase(confirmation) || "sim".equalsIgnoreCase(confirmation)) {
                    manager.remove(name);
                    System.out.println("Funcionário removido com sucesso.");
                } else {
                    System.out.println("Operação cancelada pelo usuário.");
                }
            } else {
                System.out.println("Funcionário " + name + " não encontrado.");
            }
        }

        System.out.println();
    }
}
