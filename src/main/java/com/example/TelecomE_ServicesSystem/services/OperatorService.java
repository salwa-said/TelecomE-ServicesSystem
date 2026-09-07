package com.example.TelecomE_ServicesSystem.services;

import com.example.TelecomE_ServicesSystem.entites.*;
import com.example.TelecomE_ServicesSystem.dto.*;
import com.example.TelecomE_ServicesSystem.exceptions.*;
import com.example.TelecomE_ServicesSystem.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@org.springframework.stereotype.Service
public class OperatorService {
    private OperatorRepository repository;
    private SpectrumLicenseRepository spectrumLicenseRepository;
    private InspectionRepository inspectionRepository;
    private ComplaintRepository complaintRepository;

    @Autowired
    public OperatorService(OperatorRepository repository, SpectrumLicenseRepository spectrumLicenseRepository, InspectionRepository inspectionRepository, ComplaintRepository complaintRepository) {
        this.repository = repository;
        this.spectrumLicenseRepository = spectrumLicenseRepository;
        this.inspectionRepository = inspectionRepository;
        this.complaintRepository = complaintRepository;
    }

    public Long create(OperatorDTO dto) {
        Operator entity = new Operator();
        entity.setIsActive(true);
        entity.setCreatedDate(new Date());
        entity.setName(dto.getName());
        entity.setLicenseNumber(dto.getLicenseNumber());
        entity.setContactEmail(dto.getContactEmail());
        entity.setCountry(dto.getCountry());
        repository.save(entity);
        return entity.getId();
    }

    public List<Operator> getAll() { return repository.getAllOperator(); }

    public Operator getById(Long id) {
        Optional<Operator> entity = repository.findById(id);
        if (entity.isEmpty() || !Boolean.TRUE.equals(entity.get().getIsActive())) throw new ResourceNotFoundException("Operator not found by id: " + id);
        return entity.get();
    }

    public Operator update(OperatorDTO dto) {
        Operator entity = getById(dto.getOperatorId());
        entity.setUpdatedDate(new Date());
        entity.setName(dto.getName());
        entity.setLicenseNumber(dto.getLicenseNumber());
        entity.setContactEmail(dto.getContactEmail());
        entity.setCountry(dto.getCountry());
        return repository.save(entity);
    }

    public Boolean deleteById(Long id) {
        Operator entity = getById(id);
        entity.setIsActive(false);
        entity.setUpdatedDate(new Date());
        repository.save(entity);
        return true;
    }
}

