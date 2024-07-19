package employeemanager;

import employeemanager.ui.TextInterface;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextInterface ui = new TextInterface(scanner);
        ui.start();
    }
}