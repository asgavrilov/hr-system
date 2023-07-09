package com.hr.controller;

import com.hr.model.Company;
import com.hr.model.CompanyType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.hr.service.CompanyService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping
    public void create(@RequestBody Company company) {
        companyService.createCompany(company);
    }

    @GetMapping("/{id}")
    public Company getCompanyById(@PathVariable Long id) {
        return companyService.findById(id);
    }

    @GetMapping
    public List<Company> findCompaniesByType(@RequestParam("type") CompanyType type) {
        return companyService.findByType(type);
    }
}