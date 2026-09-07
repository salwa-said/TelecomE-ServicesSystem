package com.example.TelecomE_ServicesSystem.services;
import com.example.TelecomE_ServicesSystem.entites.*;
import com.example.TelecomE_ServicesSystem.dto.*;
import com.example.TelecomE_ServicesSystem.exceptions.*;
import com.example.TelecomE_ServicesSystem.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.time.LocalDate;

@org.springframework.stereotype.Service
public class MilestoneService {
    private MilestoneRepository repository;
    private ProjectRepository projectRepository;

    @Autowired
    public MilestoneService(MilestoneRepository repository, ProjectRepository projectRepository) {
        this.repository = repository;
        this.projectRepository = projectRepository;
    }

    public Long create(MilestoneDTO dto) {
        Milestone entity = new Milestone();
        entity.setIsActive(true);
        entity.setCreatedDate(new Date());
        entity.setTitle(dto.getTitle());
        entity.setDueDate(dto.getDueDate());
        entity.setStatus(dto.getStatus());
        if (dto.getProjectId() != null) entity.setProject(projectRepository.getById(dto.getProjectId()));
        repository.save(entity);
        return entity.getId();
    }

    public List<Milestone> getAll() { return repository.getAllMilestone(); }

    public Milestone getById(Long id) {
        Optional<Milestone> entity = repository.findById(id);
        if (entity.isEmpty() || !Boolean.TRUE.equals(entity.get().getIsActive())) throw new ResourceNotFoundException("Milestone not found by id: " + id);
        return entity.get();
    }

    public Milestone update(MilestoneDTO dto) {
        Milestone entity = getById(dto.getMilestoneId());
        entity.setUpdatedDate(new Date());
        entity.setTitle(dto.getTitle());
        entity.setDueDate(dto.getDueDate());
        entity.setStatus(dto.getStatus());
        if (dto.getProjectId() != null) entity.setProject(projectRepository.getById(dto.getProjectId()));
        return repository.save(entity);
    }

    public Boolean deleteById(Long id) {
        Milestone entity = getById(id);
        entity.setIsActive(false);
        entity.setUpdatedDate(new Date());
        repository.save(entity);
        return true;
    }
}
