package com.example.companymicroservice.company.service;

import com.example.companymicroservice.company.Company;
import com.example.companymicroservice.company.ICompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService implements ICompanyService {

    private final ICompanyRepository companyRepository;

    public CompanyService(ICompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getById(Long companyId) {
        return companyRepository.findById(companyId).orElse(null);
    }

    @Override
    public Company createCompany(Company company) {
        return companyRepository.save(company);
    }

    @Override
    public Company updateByID(Company updatedCompany, Long companyId) {
        Company company  = companyRepository.findById(companyId).orElse(null);

        company.setName(updatedCompany.getName());
        company.setDescription(updatedCompany.getDescription());

        return companyRepository.save(company);
    }

    @Override
    public void deleteById(Long companyId) {
        companyRepository.deleteById(companyId);
    }
}
