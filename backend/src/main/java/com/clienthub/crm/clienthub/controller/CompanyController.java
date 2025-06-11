package com.clienthub.crm.clienthub.controller;

import com.clienthub.crm.clienthub.model.Company;
import com.clienthub.crm.clienthub.service.CompanyService;
import com.clienthub.crm.clienthub.dto.CompanyRequest;
import com.clienthub.crm.clienthub.dto.CompanyResponse;
import com.clienthub.crm.clienthub.dto.mapper.CompanyResponseMapper;
import com.clienthub.crm.clienthub.dto.mapper.CompanyRequestMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/companies")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @GetMapping
    public List<CompanyResponse> getAllCompanies() {
        return companyService.getAllCompanies().stream().map(CompanyResponseMapper::toDto).toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CompanyResponse> getCompanyById(@PathVariable Long id) {
        return ResponseEntity.ok(CompanyResponseMapper.toDto(companyService.getCompanyById(id)));
    }

    @PostMapping
    public ResponseEntity<CompanyResponse> createCompany(@RequestBody CompanyRequest companyRequest) {
        Company created = companyService.createCompany(CompanyRequestMapper.toEntity(companyRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(CompanyResponseMapper.toDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CompanyResponse> updateCompany(@PathVariable Long id, @RequestBody CompanyRequest companyRequest) {
        Company updated = companyService.updateCompany(id, CompanyRequestMapper.toEntity(companyRequest));
        return ResponseEntity.ok(CompanyResponseMapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "/{id}/avatar")
    public ResponseEntity<CompanyResponse> uploadAvatar(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        Company updated = companyService.oneShotUploadAvatar(id, file);
        return ResponseEntity.ok(CompanyResponseMapper.toDto(updated));
    }
}
