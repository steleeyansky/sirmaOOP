package com.staffmanagement.service;

import com.staffmanagement.exception.EmployeeDataSaveException;
import com.staffmanagement.model.Employee;
import com.staffmanagement.util.FileReaderUtil;
import com.staffmanagement.util.FileWriterUtil;

import java.io.IOException;
import java.util.List;

public class StaffService implements IEmployeeService {
    private final FileReaderUtil reader;
    private final FileWriterUtil writer;
    private List<Employee> employees;

    public StaffService(FileReaderUtil reader, FileWriterUtil writer) {
        this.reader = reader;
        this.writer = writer;
    }

    @Override
    public void loadEmployees() throws IOException {
        // This will load the employees from the CSV file in the resources folder
        employees = reader.loadEmployeesFromCSV("data.csv");
        // Further processing if necessary...
    }

    @Override
    public void saveEmployees() throws EmployeeDataSaveException {
        try {
            writer.saveEmployeesToCSV("data.csv", employees);
        } catch (Exception e) {
            throw new EmployeeDataSaveException("Failed to save employee data", e);
        }
    }


    @Override
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    @Override
    public void editEmployee(int id, Employee updatedEmployee) {
        // Implement the logic to edit an employee
    }

    @Override
    public void fireEmployee(int id) {
        // Implement the logic to fire an employee
    }

    @Override
    public List<Employee> listEmployees() {
        // Implement the logic to list all employees
        return employees;
    }

    @Override
    public Employee searchById(int id) {
        // Implement the logic to search for an employee by ID
        return null;
    }

    @Override
    public List<Employee> searchByDepartment(String department) {
        // Implement the logic to search for employees by department
        return null;
    }

    @Override
    public List<Employee> searchByName(String name) {
        // Implement the logic to search for employees by name
        return null;
    }
}
