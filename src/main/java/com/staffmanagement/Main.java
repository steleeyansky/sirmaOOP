package com.staffmanagement;
import com.staffmanagement.manager.Manager;
import com.staffmanagement.manager.StaffManager;
import com.staffmanagement.model.Employee;
import com.staffmanagement.service.Service;
import com.staffmanagement.service.StaffService;
import com.staffmanagement.util.FileReaderUtil;
import com.staffmanagement.util.FileWriterUtil;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        FileReaderUtil reader = new FileReaderUtil();
        FileWriterUtil writer = new FileWriterUtil();
        Service service = new StaffService(reader, writer);
        Manager manager = new StaffManager(service);

        System.out.println("Welcome to Staff Management System");
        displayCommands();

        try {
            service.loadEmployees();
        } catch (IOException e) {
            System.err.println("Error loading employees: " + e.getMessage());
            return;
        }

        Scanner scanner = new Scanner(System.in);
        boolean isRunning = true;
        while (isRunning) {
            System.out.print("Enter command: ");
            String command = scanner.nextLine();
            if ("exit".equals(command)) {
                isRunning = false;
            } else {
                manager.execute(command);
            }
        }

        try {
            service.saveEmployees();
        } catch (IOException e) {
            System.err.println("Error saving employees: " + e.getMessage());

        }

        scanner.close(); // Close the scanner before exiting the program
    }

    private static void displayCommands() {
        System.out.println("Available commands:");
        System.out.println("1. add - Add a new employee");
        System.out.println("2. edit - Edit an existing employee");
        System.out.println("3. fire - Fire an employee");
        System.out.println("4. list - List all employees");
        System.out.println("5. search - Search for an employee");
        System.out.println("6. exit - Exit and Save employee data");
    }

}
