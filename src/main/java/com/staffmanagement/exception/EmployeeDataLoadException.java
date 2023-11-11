package com.staffmanagement.exception;

import java.io.IOException;

public class EmployeeDataLoadException extends IOException {
    public EmployeeDataLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
