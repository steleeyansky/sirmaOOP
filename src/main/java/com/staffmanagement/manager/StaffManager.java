package com.staffmanagement.manager;

import com.staffmanagement.model.Employee;
import com.staffmanagement.service.IEmployeeService;
import com.staffmanagement.service.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class StaffManager implements Manager  {
    private final Service service;
    private final IEmployeeService employeeService;
    private final Scanner scanner = new Scanner(System.in);
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private boolean hasUnsavedChanges = false;
    public StaffManager(Service service) {
        this.service = service;
        this.employeeService = (IEmployeeService) service; // Cast here
    }

    public void execute(String userInput) {
        String command;
        switch (userInput) {
            case "1":
            case "add":
                command = "add";
                break;
            case "2":
            case "edit":
                command = "edit";
                break;
            case "3":
            case "fire":
                command = "fire";
                break;
            case "4":
            case "list":
                listEmployees(); // Show employees from memory
                return; // Exit this method early
            case "5":
            case "search":
                command = "search";
                break;
            case "6":
            case "exit":
                saveAndExit(); // Save on exit
                return; // Exit this method early
            default:
                System.out.println("Unknown command. Please try again.");
                return; // Exit this method early
        }

        // Now we have the command, we can perform the action
        performAction(command);
    }

    private void performAction(String command) {
        switch (command) {
            case "add":
                addEmployee();
                break;
            case "edit":
                editEmployee();
                break;
            case "fire":
                fireEmployee();
                break;
            case "search":
                searchEmployee();
                break;
        }
    }

    private void addEmployee() {
        System.out.println("Enter employee ID:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the remaining newline

        System.out.println("Enter employee name:");
        String name = scanner.nextLine();

        System.out.println("Enter start date (yyyy-MM-dd):");
        String startDateStr = scanner.nextLine();
        LocalDate startDate = LocalDate.parse(startDateStr, DATE_FORMATTER);

        System.out.println("Enter end date (yyyy-MM-dd) or press ENTER if none:");
        String endDateStr = scanner.nextLine();
        LocalDate endDate = null;
        if (!endDateStr.isEmpty()) {
            endDate = LocalDate.parse(endDateStr, DATE_FORMATTER);
        }

        System.out.println("Enter department:");
        String department = scanner.nextLine();

        System.out.println("Enter role:");
        String role = scanner.nextLine();

        System.out.println("Enter salary:");
        double salary = scanner.nextDouble();
        scanner.nextLine(); // Consume the remaining newline

        Employee employee = new Employee(id, name, department, role, salary, startDate.format(DATE_FORMATTER), endDate != null ? endDate.format(DATE_FORMATTER) : null);
        employee.setActive(true);


        employeeService.addEmployee(employee);
        System.out.println("Employee added successfully.");

        hasUnsavedChanges = true; // Indicate that there are unsaved changes
    }


    private void editEmployee() {
        System.out.println("Enter the ID of the employee you want to edit:");

        int id = scanner.nextInt();
        scanner.nextLine(); // Consume the newline

        Employee existingEmployee = employeeService.searchById(id);
        if (existingEmployee == null) {
            System.out.println("Employee not found.");
            return;
        }

        System.out.println("Enter new name or press ENTER to skip:");
        String name = scanner.nextLine();
        if (!name.isEmpty()) {
            existingEmployee.setName(name);
        }

        System.out.println("Enter new department or press ENTER to skip:");
        String department = scanner.nextLine();
        if (!department.isEmpty()) {
            existingEmployee.setDepartment(department);
        }

        System.out.println("Enter new role or press ENTER to skip:");
        String role = scanner.nextLine();
        if (!role.isEmpty()) {
            existingEmployee.setRole(role);
        }

        System.out.println("Enter new salary or press ENTER to skip:");
        String salaryInput = scanner.nextLine();
        if (!salaryInput.isEmpty()) {
            double salary = Double.parseDouble(salaryInput);
            existingEmployee.setSalary(salary);
        }

        employeeService.editEmployee(id, existingEmployee);
        System.out.println("Employee details updated successfully.");
        hasUnsavedChanges = true;
    }

    private void searchEmployee() {
        while (true) {
            System.out.println("Search by:");
            System.out.println("1. Name");
            System.out.println("2. ID");
            System.out.println("3. Department");
            System.out.println("4. Return to previous menu");

            System.out.print("Enter option (1-4): ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    searchByName();
                    break;
                case 2:
                    searchById();
                    break;
                case 3:
                    searchByDepartment();
                    break;
                case 4:
                    return; // Exit the search submenu
                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }
    }

    private void searchByName() {
        System.out.print("Enter name to search: ");
        String name = scanner.nextLine();
        List<Employee> employees = employeeService.searchByName(name);
        if (employees.isEmpty()) {
            System.out.println("No employees found with the name: " + name);
        } else {
            employees.forEach(employee -> System.out.println(employee.getName() + " - " + employee.getDepartment()));
        }
    }

    private void searchById() {
        System.out.print("Enter ID to search: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        Employee employee = employeeService.searchById(id);
        if (employee == null) {
            System.out.println("No employee found with the ID: " + id);
        } else {
            System.out.println(employee.getName() + " - " + employee.getDepartment());
        }
    }

    private void searchByDepartment() {
        System.out.print("Enter department to search: ");
        String department = scanner.nextLine();
        List<Employee> employees = employeeService.searchByDepartment(department);
        if (employees.isEmpty()) {
            System.out.println("No employees found in the department: " + department);
        } else {
            employees.forEach(employee -> System.out.println(employee.getName() + " - " + employee.getDepartment()));
        }
    }

    private void fireEmployee() {
        System.out.println("Enter the ID of the employee you want to fire:");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline left-over

        employeeService.fireEmployee(id);
        System.out.println("Employee fired successfully.");
        hasUnsavedChanges = true;
    }

    private void listEmployees() {
        List<Employee> employees = employeeService.listEmployees();
        if (employees.isEmpty()) {
            System.out.println("There are no employees to display.");
            return;
        }
        System.out.println("Listing all employees:");
        for (Employee employee : employees) {
            System.out.println(employee.getName() );
        }
    }


    private void saveAndExit() {
        if (hasUnsavedChanges) {
            try {
                employeeService.saveEmployees();
                System.out.println("Employee data saved successfully.");
            } catch (Exception e) {
                System.err.println("Error saving employees: " + e.getMessage());
                // Handle the error appropriately
            }
        }
        System.exit(0);
    }
}
