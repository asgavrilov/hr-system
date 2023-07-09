package com.hr.dao;

import com.hr.model.Company;
import com.hr.model.CompanyType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface  CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findByCompanyType(CompanyType type);

    @Query(value = "select c from Company c where c.companyType = ?1")
    List<Company> getByCompanyType(CompanyType type);

    @Query(value = "select * from companies where company_type = ?1", nativeQuery = true)
    List<Company> selectByCompanyType(String type);
}
