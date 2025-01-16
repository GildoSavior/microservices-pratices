package com.example.companymicroservice.company;

import com.example.companymicroservice.company.service.ICompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies")
public class CompanyController {

    private final ICompanyService companyService;

    public CompanyController(ICompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<Company> getAllCompanies() {
        return companyService.getAllCompanies();
    }

    @GetMapping("/{companyId}")
    public ResponseEntity<Company> getById(@PathVariable Long companyId) {

        Company company = companyService.getById(companyId);

        return ResponseEntity.ok(company);

    }

    @PostMapping
    public ResponseEntity<Company> create(@RequestBody Company company) {

        Company newCompany = companyService.createCompany(company);

        return ResponseEntity.ok(newCompany);

    }

    @PutMapping("/{companyId}")
    public ResponseEntity<Company> update(@RequestBody Company company, @PathVariable Long companyId){

        Company companyToUpdate = companyService.updateByID(company, companyId);

        return ResponseEntity.ok(companyToUpdate);

    }

    @DeleteMapping("/{companyId}")
    public ResponseEntity<String> delete(@PathVariable Long companyId) {
        companyService.deleteById(companyId);

        return ResponseEntity.ok("Delete success");
    }
}
