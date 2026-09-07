package com.example.TelecomE_ServicesSystem.services;
import com.example.TelecomE_ServicesSystem.dto.*;
import com.example.TelecomE_ServicesSystem.entites.Citizen;
import com.example.TelecomE_ServicesSystem.exceptions.*;
import com.example.TelecomE_ServicesSystem.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
@org.springframework.stereotype.Service
public class CitizenService {
    private CitizenRepository repository;
    private ApplicationRepository applicationRepository;
    private ComplaintRepository complaintRepository;
    private DomainRegistrationRepository domainRegistrationRepository;

    @Autowired
    public CitizenService(CitizenRepository repository, ApplicationRepository applicationRepository, ComplaintRepository complaintRepository, DomainRegistrationRepository domainRegistrationRepository) {
        this.repository = repository;
        this.applicationRepository = applicationRepository;
        this.complaintRepository = complaintRepository;
        this.domainRegistrationRepository = domainRegistrationRepository;
    }

    public Long create(CitizenDTO dto) {
        Citizen entity = new Citizen();
        entity.setIsActive(true);
        entity.setCreatedDate(new Date());
        entity.setName(dto.getName());
        entity.setNationalId(dto.getNationalId());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setEmail(dto.getEmail());
        repository.save(entity);
        return entity.getId();
    }

    public List<Citizen> getAll() {
        return repository.getAllCitizen();
    }

    public Citizen getById(Long id) {
        Optional<Citizen> entity = repository.findById(id);
        if (entity.isEmpty() || !Boolean.TRUE.equals(entity.get().getIsActive()))
            throw new ResourceNotFoundException("Citizen not found by id: " + id);
        return entity.get();
    }

    public Citizen update(CitizenDTO dto) {
        Citizen entity = getById(dto.getCitizenId());
        entity.setUpdatedDate(new Date());
        entity.setName(dto.getName());
        entity.setNationalId(dto.getNationalId());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setEmail(dto.getEmail());
        return repository.save(entity);
    }

    public Boolean deleteById(Long id) {
        Citizen entity = getById(id);
        entity.setIsActive(false);
        entity.setUpdatedDate(new Date());
        repository.save(entity);
        return true;
    }
}
