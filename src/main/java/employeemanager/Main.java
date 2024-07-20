package employeemanager;

import employeemanager.logic.EmployeeManager;
import employeemanager.ui.TextInterface;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        EmployeeManager manager = new EmployeeManager();
        TextInterface ui = new TextInterface(scanner, manager);
        ui.start();
    }
}