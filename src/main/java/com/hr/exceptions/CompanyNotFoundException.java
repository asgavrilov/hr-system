package com.hr.exceptions;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(long id) {
        super("Company with id = " + id + "  not found");
    }
}
