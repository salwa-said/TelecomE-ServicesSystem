package com.example.TelecomE_ServicesSystem.services;
import com.example.TelecomE_ServicesSystem.entites.*;
import com.example.TelecomE_ServicesSystem.dto.*;
import com.example.TelecomE_ServicesSystem.exceptions.*;
import com.example.TelecomE_ServicesSystem.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@org.springframework.stereotype.Service
public class VendorService {
    private VendorRepository repository;
    private ProjectRepository projectRepository;

    @Autowired
    public VendorService(VendorRepository repository, ProjectRepository projectRepository) {
        this.repository = repository;
        this.projectRepository = projectRepository;
    }

    public Long create(VendorDTO dto) {
        Vendor entity = new Vendor();
        entity.setIsActive(true);
        entity.setCreatedDate(new Date());
        entity.setName(dto.getName());
        entity.setContactEmail(dto.getContactEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setCountry(dto.getCountry());
        repository.save(entity);
        return entity.getId();
    }

    public List<Vendor> getAll() { return repository.getAllVendor(); }

    public Vendor getById(Long id) {
        Optional<Vendor> entity = repository.findById(id);
        if (entity.isEmpty() || !Boolean.TRUE.equals(entity.get().getIsActive())) throw new ResourceNotFoundException("Vendor not found by id: " + id);
        return entity.get();
    }

    public Vendor update(VendorDTO dto) {
        Vendor entity = getById(dto.getVendorId());
        entity.setUpdatedDate(new Date());
        entity.setName(dto.getName());
        entity.setContactEmail(dto.getContactEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setCountry(dto.getCountry());
        return repository.save(entity);
    }

    public Boolean deleteById(Long id) {
        Vendor entity = getById(id);
        entity.setIsActive(false);
        entity.setUpdatedDate(new Date());
        repository.save(entity);
        return true;
    }
}