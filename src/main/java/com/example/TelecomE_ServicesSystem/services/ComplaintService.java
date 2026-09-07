package com.example.TelecomE_ServicesSystem.services;
import com.example.TelecomE_ServicesSystem.entites.*;
import com.example.TelecomE_ServicesSystem.dto.*;
import com.example.TelecomE_ServicesSystem.repositories.*;
import com.example.TelecomE_ServicesSystem.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.*;

@org.springframework.stereotype.Service
public class ComplaintService {
    private ComplaintRepository repository;
    private CitizenRepository citizenRepository;
    private OperatorRepository operatorRepository;
    private OfficerRepository officerRepository;

    @Autowired
    public ComplaintService(ComplaintRepository repository, CitizenRepository citizenRepository, OperatorRepository operatorRepository, OfficerRepository officerRepository) {
        this.repository = repository;
        this.citizenRepository = citizenRepository;
        this.operatorRepository = operatorRepository;
        this.officerRepository = officerRepository;
    }

    public Long create(ComplaintDTO dto) {
        Citizen c = citizenRepository.getById(dto.getCitizenId());
        Operator o = operatorRepository.getById(dto.getOperatorId());
        if (c == null || !Boolean.TRUE.equals(c.getIsActive()))
            throw new ResourceNotFoundException("Citizen not found or inactive: " + dto.getCitizenId());
        if (o == null || !Boolean.TRUE.equals(o.getIsActive()))
            throw new ResourceNotFoundException("Operator not found or inactive: " + dto.getOperatorId());
        Complaint e = new Complaint();
        e.setIsActive(true);
        e.setCreatedDate(new Date());
        e.setSubject(dto.getSubject());
        e.setDescription(dto.getDescription());
        e.setStatus(dto.getStatus());
        e.setFiledDate(dto.getFiledDate());
        e.setCitizen(c);
        e.setOperator(o);
        if (dto.getOfficerId() != null) e.setOfficer(officerRepository.getById(dto.getOfficerId()));
        return repository.save(e).getId();
    }

    public List<Complaint> getAll() {
        return repository.getAllComplaint();
    }

    public Complaint getById(Long id) {
        Optional<Complaint> e = repository.findById(id);
        if (e.isEmpty() || !Boolean.TRUE.equals(e.get().getIsActive()))
            throw new ResourceNotFoundException("Complaint not found by id: " + id);
        return e.get();
    }

    public Complaint update(ComplaintDTO dto) {
        Complaint e = getById(dto.getComplaintId());
        e.setUpdatedDate(new Date());
        e.setSubject(dto.getSubject());
        e.setDescription(dto.getDescription());
        e.setStatus(dto.getStatus());
        e.setFiledDate(dto.getFiledDate());
        if (dto.getOfficerId() != null) e.setOfficer(officerRepository.getById(dto.getOfficerId()));
        return repository.save(e);
    }

    public Boolean deleteById(Long id) {
        Complaint e = getById(id);
        e.setIsActive(false);
        e.setUpdatedDate(new Date());
        repository.save(e);
        return true;
    }

    public Complaint file(ComplaintDTO dto) {
        dto.setStatus("open");
        dto.setFiledDate(LocalDate.now());
        return getById(create(dto));
    }

    public Complaint assign(Long id, Long officerId) {
        Complaint e = getById(id);
        Officer o = officerRepository.getById(officerId);
        if (o == null || !Boolean.TRUE.equals(o.getIsActive()))
            throw new ResourceNotFoundException("Officer not found or inactive: " + officerId);
        e.setOfficer(o);
        e.setStatus("in progress");
        e.setUpdatedDate(new Date());
        return repository.save(e);
    }

    public Complaint resolve(Long id) {
        Complaint e = getById(id);
        if ("resolved".equalsIgnoreCase(e.getStatus())) throw new BusinessException("Complaint is already resolved");
        e.setStatus("resolved");
        e.setUpdatedDate(new Date());
        return repository.save(e);
    }

    public List<Complaint> open() {
        return repository.findOpenComplaints();
    }

    public List<Complaint> openForOperator(Long operatorId) {
        operatorRepository.getById(operatorId);
        return repository.findOpenByOperator(operatorId);
    }
}


