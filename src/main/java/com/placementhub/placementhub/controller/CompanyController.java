package com.placementhub.placementhub.controller;

import com.placementhub.placementhub.dto.CompanyRequest;
import com.placementhub.placementhub.entity.Company;
import com.placementhub.placementhub.service.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping
    public ResponseEntity<Company> createCompany(
            @RequestBody CompanyRequest request) {

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(companyService.createCompany(request));
    }

    @GetMapping
    public ResponseEntity<List<Company>> getAllCompanies() {

        return ResponseEntity.ok(
                companyService.getAllCompanies()
        );
    }
}