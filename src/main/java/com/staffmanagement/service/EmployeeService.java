package com.staffmanagement.service;

import com.staffmanagement.model.Employee;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeService implements IEmployeeService {
    private final List<Employee> employees = new ArrayList<>();
    public void setEmployees(List<Employee> employees) {
        this.employees.clear();
        this.employees.addAll(employees);
    }

    @Override
    public void addEmployee(Employee employee) {
        employees.add(employee);
    }


    @Override
    public void editEmployee(int id, Employee updatedEmployee) {
        employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .ifPresent(employee -> {
                    employee.setName(updatedEmployee.getName());
                    employee.setDepartment(updatedEmployee.getDepartment());
                    employee.setRole(updatedEmployee.getRole());
                    employee.setSalary(updatedEmployee.getSalary());
                    employee.setStartDate(updatedEmployee.getStartDate());
                });
    }

    @Override
    public void fireEmployee(int id) {
        employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .ifPresent(employee -> {
                    employee.setEndDate(LocalDate.now());
                    employee.setActive(false);
                });
    }
    @Override
    public List<Employee> listEmployees() {
        return new ArrayList<>(employees);
    }

    @Override
    public Employee searchById(int id) {
        return employees.stream()
                .filter(employee -> employee.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Employee> searchByDepartment(String department) {
        return employees.stream()
                .filter(employee -> employee.getDepartment().equalsIgnoreCase(department))
                .collect(Collectors.toList());
    }

    @Override
    public List<Employee> searchByName(String name) {
        return employees.stream()
                .filter(employee -> employee.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    @Override
    public void loadEmployees() throws IOException {

    }

    @Override
    public void saveEmployees() throws IOException {

    }
}