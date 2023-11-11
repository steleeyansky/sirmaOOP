package com.staffmanagement.model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Employee {
    private int id;
    private String name;
    private String department;
    private String role;
    private double salary;
    private LocalDate startDate;
    private LocalDate endDate;
    private boolean isActive;

    // Assuming your JSON dates are in the standard "yyyy-MM-dd" format
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public Employee(int id, String name, String department, String role, double salary, String startDateStr, String endDateStr) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.role = role;
        this.salary = salary;

        if (startDateStr != null && !startDateStr.isEmpty()) {
            this.startDate = LocalDate.parse(startDateStr, DATE_FORMATTER);
        }

        if (endDateStr != null && !endDateStr.isEmpty()) {
            this.endDate = LocalDate.parse(endDateStr, DATE_FORMATTER);
        }
    }

    public Employee() {

    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = LocalDate.parse(startDate, Employee.DATE_FORMATTER);
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = LocalDate.parse(endDate, DATE_FORMATTER);
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }


    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", department='" + department + '\'' +
                ", role='" + role + '\'' +
                ", salary=" + salary +
                ", startDate='" + startDate + '\'' +
                '}';
    }
}
