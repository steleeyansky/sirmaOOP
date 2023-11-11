package com.staffmanagement.service;

import com.staffmanagement.model.Employee;

import java.util.List;

public interface IEmployeeService extends Service {
    void addEmployee(Employee employee);
    void editEmployee(int id, Employee updatedEmployee);
    void fireEmployee(int id);
    List<Employee> listEmployees();
    Employee searchById(int id);
    List<Employee> searchByDepartment(String department);
    List<Employee> searchByName(String name);
    // Any additional methods related to employee management
}