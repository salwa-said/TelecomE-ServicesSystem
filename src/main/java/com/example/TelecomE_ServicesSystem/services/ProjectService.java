package com.example.TelecomE_ServicesSystem.services;
import com.example.TelecomE_ServicesSystem.entites.*;
import com.example.TelecomE_ServicesSystem.dto.*;
import com.example.TelecomE_ServicesSystem.repositories.*;
import com.example.TelecomE_ServicesSystem.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;

@org.springframework.stereotype.Service
public class ProjectService {
    private ProjectRepository repository;
    private MinistryRepository ministryRepository;
    private VendorRepository vendorRepository;
    private MilestoneRepository milestoneRepository;

    @Autowired
    public ProjectService(ProjectRepository repository, MinistryRepository ministryRepository, VendorRepository vendorRepository, MilestoneRepository milestoneRepository) {
        this.repository = repository;
        this.ministryRepository = ministryRepository;
        this.vendorRepository = vendorRepository;
        this.milestoneRepository = milestoneRepository;
    }

    public Long create(ProjectDTO dto) {
        Ministry m = ministryRepository.getById(dto.getMinistryId());
        if (m == null || !Boolean.TRUE.equals(m.getIsActive()))
            throw new ResourceNotFoundException("Ministry not found or inactive: " + dto.getMinistryId());
        Project e = new Project();
        e.setIsActive(true);
        e.setCreatedDate(new Date());
        e.setTitle(dto.getTitle());
        e.setBudget(dto.getBudget());
        e.setStartDate(dto.getStartDate());
        e.setStatus(dto.getStatus());
        e.setMinistry(m);
        if (dto.getVendorId() != null) e.setVendor(vendorRepository.getById(dto.getVendorId()));
        return repository.save(e).getId();
    }

    public List<Project> getAll() {
        return repository.getAllProject();
    }

    public Project getById(Long id) {
        Optional<Project> e = repository.findById(id);
        if (e.isEmpty() || !Boolean.TRUE.equals(e.get().getIsActive()))
            throw new ResourceNotFoundException("Project not found by id: " + id);
        return e.get();
    }

    public Project update(ProjectDTO dto) {
        Project e = getById(dto.getProjectId());
        e.setUpdatedDate(new Date());
        e.setTitle(dto.getTitle());
        e.setBudget(dto.getBudget());
        e.setStartDate(dto.getStartDate());
        e.setStatus(dto.getStatus());
        if (dto.getMinistryId() != null) e.setMinistry(ministryRepository.getById(dto.getMinistryId()));
        if (dto.getVendorId() != null) e.setVendor(vendorRepository.getById(dto.getVendorId()));
        return repository.save(e);
    }

    public Boolean deleteById(Long id) {
        Project e = getById(id);
        e.setIsActive(false);
        e.setUpdatedDate(new Date());
        repository.save(e);
        return true;
    }

    public List<Project> overBudget(BigDecimal threshold) {
        return repository.findOverBudget(threshold);
    }

    public Project addMilestone(Long projectId, MilestoneDTO dto) {
        Project p = getById(projectId);
        Milestone e = new Milestone();
        e.setIsActive(true);
        e.setCreatedDate(new Date());
        e.setTitle(dto.getTitle());
        e.setDueDate(dto.getDueDate());
        e.setStatus("pending");
        e.setProject(p);
        milestoneRepository.save(e);
        return repository.save(p);
    }

    public Milestone completeMilestone(Long milestoneId) {
        Optional<Milestone> op = milestoneRepository.findById(milestoneId);
        if (op.isEmpty() || !Boolean.TRUE.equals(op.get().getIsActive()))
            throw new ResourceNotFoundException("Milestone not found by id: " + milestoneId);
        Milestone e = op.get();
        e.setStatus("completed");
        e.setUpdatedDate(new Date());
        return milestoneRepository.save(e);
    }

    public Map<String, Object> stats(Long projectId) {
        Project p = getById(projectId);
        List<Milestone> list = milestoneRepository.getAllMilestone().stream().filter(m -> m.getProject() != null && m.getProject().getId().equals(projectId)).toList();
        long completed = list.stream().filter(m -> "completed".equalsIgnoreCase(m.getStatus())).count();
        double pct = list.isEmpty() ? 0.0 : (completed * 100.0 / list.size());
        Map<String, Object> result = new LinkedHashMap<>();
        result.put("projectId", p.getId());
        result.put("projectTitle", p.getTitle());
        result.put("totalMilestones", list.size());
        result.put("completedMilestones", completed);
        result.put("completionPercentage", pct);
        return result;
    }
}
