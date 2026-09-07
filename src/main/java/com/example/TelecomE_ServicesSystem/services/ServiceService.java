package com.example.TelecomE_ServicesSystem.services;
import com.example.TelecomE_ServicesSystem.entites.*;
import com.example.TelecomE_ServicesSystem.dto.*;
import com.example.TelecomE_ServicesSystem.exceptions.*;
import com.example.TelecomE_ServicesSystem.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.math.BigDecimal;

@org.springframework.stereotype.Service
public class ServiceService {
    private ServiceRepository repository;
    private DepartmentRepository departmentRepository;
    private ApplicationRepository applicationRepository;

    @Autowired
    public ServiceService(ServiceRepository repository, DepartmentRepository departmentRepository, ApplicationRepository applicationRepository) {
        this.repository = repository;
        this.departmentRepository = departmentRepository;
        this.applicationRepository = applicationRepository;
    }

    public Long create(ServiceDTO dto) {
        Service entity = new Service();
        entity.setIsActive(true);
        entity.setCreatedDate(new Date());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setFee(dto.getFee());
        entity.setProcessingDays(dto.getProcessingDays());
        if (dto.getDepartmentId() != null) entity.setDepartment(departmentRepository.getById(dto.getDepartmentId()));
        repository.save(entity);
        return entity.getId();
    }

    public List<Service> getAll() { return repository.getAllService(); }

    public Service getById(Long id) {
        Optional<Service> entity = repository.findById(id);
        if (entity.isEmpty() || !Boolean.TRUE.equals(entity.get().getIsActive())) throw new ResourceNotFoundException("Service not found by id: " + id);
        return entity.get();
    }

    public Service update(ServiceDTO dto) {
        Service entity = getById(dto.getServiceId());
        entity.setUpdatedDate(new Date());
        entity.setName(dto.getName());
        entity.setDescription(dto.getDescription());
        entity.setFee(dto.getFee());
        entity.setProcessingDays(dto.getProcessingDays());
        if (dto.getDepartmentId() != null) entity.setDepartment(departmentRepository.getById(dto.getDepartmentId()));
        return repository.save(entity);
    }

    public Boolean deleteById(Long id) {
        Service entity = getById(id);
        entity.setIsActive(false);
        entity.setUpdatedDate(new Date());
        repository.save(entity);
        return true;
    }
}

