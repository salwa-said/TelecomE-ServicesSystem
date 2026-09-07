package com.example.TelecomE_ServicesSystem.services;
import com.example.TelecomE_ServicesSystem.entites.*;
import com.example.TelecomE_ServicesSystem.dto.*;
import com.example.TelecomE_ServicesSystem.exceptions.*;
import com.example.TelecomE_ServicesSystem.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@org.springframework.stereotype.Service
public class DepartmentService {
    private DepartmentRepository repository;
    private MinistryRepository ministryRepository;
    private OfficerRepository officerRepository;
    private ServiceRepository serviceRepository;

    @Autowired
    public DepartmentService(DepartmentRepository repository, MinistryRepository ministryRepository, OfficerRepository officerRepository, ServiceRepository serviceRepository) {
        this.repository = repository;
        this.ministryRepository = ministryRepository;
        this.officerRepository = officerRepository;
        this.serviceRepository = serviceRepository;
    }

    public Long create(DepartmentDTO dto) {
        Department entity = new Department();
        entity.setIsActive(true);
        entity.setCreatedDate(new Date());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        if (dto.getMinistryId() != null) entity.setMinistry(ministryRepository.getById(dto.getMinistryId()));
        repository.save(entity);
        return entity.getId();
    }

    public List<Department> getAll() { return repository.getAllDepartment(); }

    public Department getById(Long id) {
        Optional<Department> entity = repository.findById(id);
        if (entity.isEmpty() || !Boolean.TRUE.equals(entity.get().getIsActive())) throw new ResourceNotFoundException("Department not found by id: " + id);
        return entity.get();
    }

    public Department update(DepartmentDTO dto) {
        Department entity = getById(dto.getDepartmentId());
        entity.setUpdatedDate(new Date());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        if (dto.getMinistryId() != null) entity.setMinistry(ministryRepository.getById(dto.getMinistryId()));
        return repository.save(entity);
    }

    public Boolean deleteById(Long id) {
        Department entity = getById(id);
        entity.setIsActive(false);
        entity.setUpdatedDate(new Date());
        repository.save(entity);
        return true;
    }
}

