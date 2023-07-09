package com.hr.service;

import com.hr.dao.CompanyRepository;
import com.hr.exceptions.CompanyNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.hr.model.Company;
import com.hr.model.CompanyType;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@AllArgsConstructor
@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Transactional
    public void createCompany(Company company) {
        company.getVacancies().forEach(vacancy -> vacancy.setCompany(company));
        // or with Cascade.Persist
        Company savedCompany = companyRepository.save(company);
        log.info("Saved company: {}", savedCompany);
    }

    @Retryable(retryFor = CompanyNotFoundException.class)
    public Company findById(long id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException(id));
    }

    public List<Company> findByType(CompanyType type) {
//        return companyRepository.findByCompanyType(type);
//        return companyRepository.getByCompanyType(type);
        return companyRepository.selectByCompanyType(type.name());
    }
}