package com.hr.service;

import lombok.Data;
import com.hr.model.ITCompany;
import org.springframework.stereotype.Component;

@Data
@Component
public class VacancyService {
    private ITCompany company;

    public void placeVacancy(String vacancy, ITCompany company) {
        System.out.println("Company with name " + company.getName() + " placed vacancy " + vacancy);
    }
}
