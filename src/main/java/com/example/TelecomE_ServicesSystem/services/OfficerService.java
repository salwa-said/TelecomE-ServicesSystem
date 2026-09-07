package com.example.TelecomE_ServicesSystem.services;
import com.example.TelecomE_ServicesSystem.entites.*;
import com.example.TelecomE_ServicesSystem.dto.*;
import com.example.TelecomE_ServicesSystem.exceptions.*;
import com.example.TelecomE_ServicesSystem.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@org.springframework.stereotype.Service
public class OfficerService {
    private OfficerRepository repository;
    private DepartmentRepository departmentRepository;
    private ApplicationRepository applicationRepository;
    private InspectionRepository inspectionRepository;
    private ComplaintRepository complaintRepository;

    @Autowired
    public OfficerService(OfficerRepository repository, DepartmentRepository departmentRepository, ApplicationRepository applicationRepository, InspectionRepository inspectionRepository, ComplaintRepository complaintRepository) {
        this.repository = repository;
        this.departmentRepository = departmentRepository;
        this.applicationRepository = applicationRepository;
        this.inspectionRepository = inspectionRepository;
        this.complaintRepository = complaintRepository;
    }

    public Long create(OfficerDTO dto) {
        Officer entity = new Officer();
        entity.setIsActive(true);
        entity.setCreatedDate(new Date());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setDesignation(dto.getDesignation());
        if (dto.getDepartmentId() != null) entity.setDepartment(departmentRepository.getById(dto.getDepartmentId()));
        repository.save(entity);
        return entity.getId();
    }

    public List<Officer> getAll() { return repository.getAllOfficer(); }

    public Officer getById(Long id) {
        Optional<Officer> entity = repository.findById(id);
        if (entity.isEmpty() || !Boolean.TRUE.equals(entity.get().getIsActive())) throw new ResourceNotFoundException("Officer not found by id: " + id);
        return entity.get();
    }

    public Officer update(OfficerDTO dto) {
        Officer entity = getById(dto.getOfficerId());
        entity.setUpdatedDate(new Date());
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setDesignation(dto.getDesignation());
        if (dto.getDepartmentId() != null) entity.setDepartment(departmentRepository.getById(dto.getDepartmentId()));
        return repository.save(entity);
    }

    public Boolean deleteById(Long id) {
        Officer entity = getById(id);
        entity.setIsActive(false);
        entity.setUpdatedDate(new Date());
        repository.save(entity);
        return true;
    }
}

