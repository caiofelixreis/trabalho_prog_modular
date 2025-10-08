package com.example.hrpayroll.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import com.example.hrpayroll.dto.CompanyPatchDTO;
import com.example.hrpayroll.model.CompanyModel;
import com.example.hrpayroll.service.CompanyService;

@RestController
@RequestMapping("/companies")
public class CompanyController {
    @Autowired
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/create")
    public ResponseEntity createCompany(@Valid @RequestBody CompanyModel companyModel) {
        companyService.create(companyModel);

        return ResponseEntity.ok().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity getCompany(@Valid @PathVariable Long id) {

        CompanyModel company = companyService.findOneById(id);

        return ResponseEntity.ok().body(company);
    }

    @GetMapping("/list")
    public Iterable<CompanyModel> listCompanies() {
        return companyService.list();
    }

    @PatchMapping("/update/{id}")
    public CompanyModel patchCompany(@PathVariable Long id, @Valid @RequestBody CompanyPatchDTO companyPatchDto) {
        return companyService.update(id, companyPatchDto);
    }
}
