package com.staffmanagement.util;

import com.opencsv.CSVWriter;
import com.staffmanagement.model.Employee;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileWriterUtil {

    public static void saveEmployeesToCSV(String filename, List<Employee> employees) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(filename))) {
            // Write the header
            String[] header = {"Id", "Name", "StartDate", "EndDate", "Department", "Role", "Salary"};
            writer.writeNext(header);

            // Write employee data
            for (Employee employee : employees) {
                String[] data = {
                        String.valueOf(employee.getId()),
                        employee.getName(),
                        employee.getStartDate() == null ? "null" : employee.getStartDate().toString(),
                        employee.getEndDate() == null ? "null" : employee.getEndDate().toString(),
                        employee.getDepartment(),
                        employee.getRole(),
                        String.valueOf(employee.getSalary())
                };
                writer.writeNext(data);
            }



            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
