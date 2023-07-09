package com.hr.model;

public class BuilderCompany extends Company {

    public BuilderCompany(String name, int employeeCount) {
        super(name, employeeCount, CompanyType.BUILDER);
    }
}
