package com.example.TelecomE_ServicesSystem.services;
import com.example.TelecomE_ServicesSystem.entites.*;
import com.example.TelecomE_ServicesSystem.dto.*;
import com.example.TelecomE_ServicesSystem.exceptions.*;
import com.example.TelecomE_ServicesSystem.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@org.springframework.stereotype.Service
public class MinistryService {
    private MinistryRepository repository;
    private DepartmentRepository departmentRepository;
    private ProjectRepository projectRepository;

    @Autowired
    public MinistryService(MinistryRepository repository, DepartmentRepository departmentRepository, ProjectRepository projectRepository) {
        this.repository = repository;
        this.departmentRepository = departmentRepository;
        this.projectRepository = projectRepository;
    }

    public Long create(MinistryDTO dto) {
        Ministry entity = new Ministry();
        entity.setIsActive(true);
        entity.setCreatedDate(new Date());
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        repository.save(entity);
        return entity.getId();
    }

    public List<Ministry> getAll() { return repository.getAllMinistry(); }

    public Ministry getById(Long id) {
        Optional<Ministry> entity = repository.findById(id);
        if (entity.isEmpty() || !Boolean.TRUE.equals(entity.get().getIsActive())) throw new ResourceNotFoundException("Ministry not found by id: " + id);
        return entity.get();
    }

    public Ministry update(MinistryDTO dto) {
        Ministry entity = getById(dto.getMinistryId());
        entity.setUpdatedDate(new Date());
        entity.setName(dto.getName());
        entity.setAddress(dto.getAddress());
        return repository.save(entity);
    }

    public Boolean deleteById(Long id) {
        Ministry entity = getById(id);
        entity.setIsActive(false);
        entity.setUpdatedDate(new Date());
        repository.save(entity);
        return true;
    }
}

