package com.example.TelecomE_ServicesSystem.services;
import com.example.TelecomE_ServicesSystem.entites.*;
import com.example.TelecomE_ServicesSystem.dto.*;
import com.example.TelecomE_ServicesSystem.repositories.*;
import com.example.TelecomE_ServicesSystem.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

@org.springframework.stereotype.Service
public class InspectionService {
    private InspectionRepository repository;
    private OperatorRepository operatorRepository;
    private OfficerRepository officerRepository;

    @Autowired
    public InspectionService(InspectionRepository repository, OperatorRepository operatorRepository, OfficerRepository officerRepository) {
        this.repository = repository;
        this.operatorRepository = operatorRepository;
        this.officerRepository = officerRepository;
    }

    public Long create(InspectionDTO dto) {
        Operator o = operatorRepository.getById(dto.getOperatorId());
        Officer f = officerRepository.getById(dto.getOfficerId());
        if (o == null || !Boolean.TRUE.equals(o.getIsActive()))
            throw new ResourceNotFoundException("Operator not found or inactive: " + dto.getOperatorId());
        if (f == null || !Boolean.TRUE.equals(f.getIsActive()))
            throw new ResourceNotFoundException("Officer not found or inactive: " + dto.getOfficerId());
        Inspection e = new Inspection();
        e.setIsActive(true);
        e.setCreatedDate(new Date());
        e.setInspectionDate(dto.getInspectionDate());
        e.setResult(dto.getResult());
        e.setNotes(dto.getNotes());
        e.setOperator(o);
        e.setOfficer(f);
        return repository.save(e).getId();
    }

    public List<Inspection> getAll() {
        return repository.getAllInspection();
    }

    public Inspection getById(Long id) {
        Optional<Inspection> e = repository.findById(id);
        if (e.isEmpty() || !Boolean.TRUE.equals(e.get().getIsActive()))
            throw new ResourceNotFoundException("Inspection not found by id: " + id);
        return e.get();
    }

    public Inspection update(InspectionDTO dto) {
        Inspection e = getById(dto.getInspectionId());
        e.setUpdatedDate(new Date());
        e.setInspectionDate(dto.getInspectionDate());
        e.setResult(dto.getResult());
        e.setNotes(dto.getNotes());
        if (dto.getOperatorId() != null) e.setOperator(operatorRepository.getById(dto.getOperatorId()));
        if (dto.getOfficerId() != null) e.setOfficer(officerRepository.getById(dto.getOfficerId()));
        return repository.save(e);
    }

    public Boolean deleteById(Long id) {
        Inspection e = getById(id);
        e.setIsActive(false);
        e.setUpdatedDate(new Date());
        repository.save(e);
        return true;
    }
}

