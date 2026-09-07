package com.example.TelecomE_ServicesSystem.services;
import com.example.TelecomE_ServicesSystem.entites.*;
import com.example.TelecomE_ServicesSystem.dto.*;
import com.example.TelecomE_ServicesSystem.repositories.*;
import com.example.TelecomE_ServicesSystem.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.*;
import java.util.*;

@org.springframework.stereotype.Service
public class SpectrumLicenseService {
    private SpectrumLicenseRepository repository;
    private OperatorRepository operatorRepository;

    @Autowired
    public SpectrumLicenseService(SpectrumLicenseRepository repository, OperatorRepository operatorRepository) {
        this.repository = repository;
        this.operatorRepository = operatorRepository;
    }

    public Long create(SpectrumLicenseDTO dto) {
        Operator o = operatorRepository.getById(dto.getOperatorId());
        if (o == null || !Boolean.TRUE.equals(o.getIsActive()))
            throw new ResourceNotFoundException("Operator not found or inactive: " + dto.getOperatorId());
        if (!dto.getExpiryDate().isAfter(dto.getIssueDate()))
            throw new BusinessException("Expiry date must be after issue date");
        SpectrumLicense e = new SpectrumLicense();
        e.setIsActive(true);
        e.setCreatedDate(new Date());
        e.setBandName(dto.getBandName());
        e.setFrequencyMhz(dto.getFrequencyMhz());
        e.setIssueDate(dto.getIssueDate());
        e.setExpiryDate(dto.getExpiryDate());
        e.setStatus(dto.getStatus());
        e.setOperator(o);
        return repository.save(e).getId();
    }

    public List<SpectrumLicense> getAll() {
        return repository.getAllSpectrumLicense();
    }

    public SpectrumLicense getById(Long id) {
        Optional<SpectrumLicense> e = repository.findById(id);
        if (e.isEmpty() || !Boolean.TRUE.equals(e.get().getIsActive()))
            throw new ResourceNotFoundException("Spectrum license not found by id: " + id);
        return e.get();
    }

    public SpectrumLicense update(SpectrumLicenseDTO dto) {
        SpectrumLicense e = getById(dto.getSpectrumLicenseId());
        if (!dto.getExpiryDate().isAfter(dto.getIssueDate()))
            throw new BusinessException("Expiry date must be after issue date");
        e.setUpdatedDate(new Date());
        e.setBandName(dto.getBandName());
        e.setFrequencyMhz(dto.getFrequencyMhz());
        e.setIssueDate(dto.getIssueDate());
        e.setExpiryDate(dto.getExpiryDate());
        e.setStatus(dto.getStatus());
        if (dto.getOperatorId() != null) e.setOperator(operatorRepository.getById(dto.getOperatorId()));
        return repository.save(e);
    }

    public Boolean deleteById(Long id) {
        SpectrumLicense e = getById(id);
        e.setIsActive(false);
        e.setUpdatedDate(new Date());
        repository.save(e);
        return true;
    }

    public List<SpectrumLicense> expiringSoon() {
        LocalDate now = LocalDate.now();
        return repository.findExpiringSoon(now, now.plusDays(30));
    }
}
