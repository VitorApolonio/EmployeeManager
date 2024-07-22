package employeemanager;

import employeemanager.logic.EmployeeManager;
import employeemanager.ui.TextInterface;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // This is done so the scanner can read portuguese characters correctly
        boolean isWindows = System.getProperty("os.name").startsWith("Windows");
        String encoding = isWindows ? "Windows-1252" : "UTF-8";

        Scanner scanner = new Scanner(System.in, encoding);

        EmployeeManager manager = new EmployeeManager();
        TextInterface ui = new TextInterface(scanner, manager);
        ui.start();
    }
}