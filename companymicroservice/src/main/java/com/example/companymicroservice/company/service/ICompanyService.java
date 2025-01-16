package com.example.companymicroservice.company.service;

import com.example.companymicroservice.company.Company;

import java.util.List;

public interface ICompanyService {
    List<Company> getAllCompanies();

    Company getById(Long companyId);

    Company createCompany(Company company);

    Company updateByID(Company company, Long companyId);

    void deleteById(Long companyId);
}
