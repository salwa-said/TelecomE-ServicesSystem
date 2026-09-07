package com.example.TelecomE_ServicesSystem.services;

import com.example.TelecomE_ServicesSystem.entites.*;
import com.example.TelecomE_ServicesSystem.dto.*;
import com.example.TelecomE_ServicesSystem.repositories.*;
import com.example.TelecomE_ServicesSystem.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@org.springframework.stereotype.Service
public class DomainRegistrationService {
    private DomainRegistrationRepository repository;
    private CitizenRepository citizenRepository;

    @Autowired
    public DomainRegistrationService(DomainRegistrationRepository repository, CitizenRepository citizenRepository) {
        this.repository = repository;
        this.citizenRepository = citizenRepository;
    }

    public Long create(DomainRegistrationDTO dto) {
        Citizen c = citizenRepository.getById(dto.getCitizenId());
        if (c == null || !Boolean.TRUE.equals(c.getIsActive()))
            throw new ResourceNotFoundException("Citizen not found or inactive: " + dto.getCitizenId());
        if (repository.findActiveByDomainName(dto.getDomainName()) != null)
            throw new BusinessException("Active domain already exists: " + dto.getDomainName());
        if (!dto.getExpiryDate().isAfter(dto.getRegisteredDate()))
            throw new BusinessException("Expiry date must be after registered date");
        DomainRegistration e = new DomainRegistration();
        e.setIsActive(true);
        e.setCreatedDate(new Date());
        e.setDomainName(dto.getDomainName().toLowerCase());
        e.setRegisteredDate(dto.getRegisteredDate());
        e.setExpiryDate(dto.getExpiryDate());
        e.setStatus(dto.getStatus());
        e.setCitizen(c);
        return repository.save(e).getId();
    }

    public List<DomainRegistration> getAll() {
        return repository.getAllDomainRegistration();
    }

    public DomainRegistration getById(Long id) {
        Optional<DomainRegistration> e = repository.findById(id);
        if (e.isEmpty() || !Boolean.TRUE.equals(e.get().getIsActive()))
            throw new ResourceNotFoundException("Domain registration not found by id: " + id);
        return e.get();
    }

    public DomainRegistration update(DomainRegistrationDTO dto) {
        DomainRegistration e = getById(dto.getDomainRegistrationId());
        if (!dto.getExpiryDate().isAfter(dto.getRegisteredDate()))
            throw new BusinessException("Expiry date must be after registered date");
        e.setUpdatedDate(new Date());
        e.setDomainName(dto.getDomainName().toLowerCase());
        e.setRegisteredDate(dto.getRegisteredDate());
        e.setExpiryDate(dto.getExpiryDate());
        e.setStatus(dto.getStatus());
        return repository.save(e);
    }

    public Boolean deleteById(Long id) {
        DomainRegistration e = getById(id);
        e.setIsActive(false);
        e.setUpdatedDate(new Date());
        repository.save(e);
        return true;
    }

    public DomainRegistration renew(Long id, java.time.LocalDate newExpiryDate) {
        DomainRegistration e = getById(id);
        if (newExpiryDate == null || !newExpiryDate.isAfter(e.getExpiryDate()))
            throw new BusinessException("New expiry date must be after current expiry date");
        e.setExpiryDate(newExpiryDate);
        e.setStatus("active");
        e.setUpdatedDate(new Date());
        return repository.save(e);
    }
}
