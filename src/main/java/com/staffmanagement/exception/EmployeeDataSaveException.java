package com.staffmanagement.exception;

import java.io.IOException;

public class EmployeeDataSaveException extends IOException {
    public EmployeeDataSaveException(String message, Throwable cause) {
        super(message, cause);
    }
}

