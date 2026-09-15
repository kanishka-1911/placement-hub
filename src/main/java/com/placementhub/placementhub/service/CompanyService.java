package com.placementhub.placementhub.service;

import com.placementhub.placementhub.dto.CompanyRequest;
import com.placementhub.placementhub.entity.Company;
import com.placementhub.placementhub.repository.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company createCompany(CompanyRequest request) {

        if (companyRepository.findByName(request.getName()).isPresent()) {
            throw new RuntimeException("Company already exists");
        }

        Company company = new Company();

        company.setName(request.getName());
        company.setLocation(request.getLocation());
        company.setWebsite(request.getWebsite());

        return companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }
}
