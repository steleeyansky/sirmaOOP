package com.staffmanagement.util;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import com.staffmanagement.model.Employee;

import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FileReaderUtil {

    public static List<Employee> loadEmployeesFromCSV(String filename) {
        List<Employee> employees = new ArrayList<>();
        try (CSVReader reader = new CSVReader(new FileReader(filename))) {
            String[] nextLine;
            boolean isHeader = true;

            while ((nextLine = reader.readNext()) != null) {
                if (isHeader) {
                    isHeader = false; // Skip the header line
                    continue;
                }

                // Parse the CSV data into an Employee object
                Employee employee = new Employee();
                employee.setId(Integer.parseInt(nextLine[0]));
                employee.setName(nextLine[1]);

                // Parse StartDate
                String startDateStr = nextLine[2];
                employee.setStartDate("null".equals(startDateStr) || startDateStr.isEmpty() ? null : LocalDate.parse(startDateStr));

                // Parse EndDate
                String endDateStr = nextLine[3];
                employee.setEndDate("null".equals(endDateStr) || endDateStr.isEmpty() ? null : LocalDate.parse(endDateStr));

                // Parse other fields...

                employees.add(employee);
            }
        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }
        return employees;
    }
}
