package com.hr.model;

import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@JsonIncludeProperties(value = {"id", "name", "about", "address", "phone", "employeeCount", "companyType", "vacancies"})
@Entity
@Table(name = "companies")
@NoArgsConstructor
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    protected String name;
    private String about;
    private String address;
    private String phone;

    @Column(name = "employee_count", nullable = false)
    private int employeeCount;

    @Column(name = "company_type")
    @Enumerated(value = EnumType.STRING)
    private CompanyType companyType;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //mappedBy column will be displayed in vacancies table
    private List<Vacancy> vacancies;

    @OneToMany(mappedBy = "company")
    private List<Profile> profiles;

    public Company(String name, String about, String address, String phone, int employeeCount, CompanyType companyType) {
        this.name = name;
        this.about = about;
        this.address = address;
        this.phone = phone;
        this.employeeCount = employeeCount;
        this.companyType = companyType;
    }

    public Company(String name, int employeeCount, CompanyType companyType) {
        this.name = name;
        this.employeeCount = employeeCount;
        this.companyType = companyType;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", about='" + about + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", employeeCount=" + employeeCount +
                ", companyType=" + companyType +
                '}';
    }
}