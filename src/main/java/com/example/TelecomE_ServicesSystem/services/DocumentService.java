package com.example.TelecomE_ServicesSystem.services;

import com.example.TelecomE_ServicesSystem.entites.*;
import com.example.TelecomE_ServicesSystem.dto.*;
import com.example.TelecomE_ServicesSystem.exceptions.*;
import com.example.TelecomE_ServicesSystem.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;
import java.time.LocalDate;

@org.springframework.stereotype.Service
public class DocumentService {
    private DocumentRepository repository;
    private ApplicationRepository applicationRepository;
    private ProjectRepository projectRepository;

    @Autowired
    public DocumentService(DocumentRepository repository, ApplicationRepository applicationRepository, ProjectRepository projectRepository) {
        this.repository = repository;
        this.applicationRepository = applicationRepository;
        this.projectRepository = projectRepository;
    }

    public Long create(DocumentDTO dto) {
        if (dto.getApplicationId() == null && dto.getProjectId() == null) throw new BusinessException("Document must belong to an application or project");
        if (dto.getApplicationId() != null && dto.getProjectId() != null) throw new BusinessException("Document can belong to an application or project, not both");
        Document entity = new Document();
        entity.setIsActive(true);
        entity.setCreatedDate(new Date());
        entity.setTitle(dto.getTitle());
        entity.setType(dto.getType());
        entity.setUploadDate(dto.getUploadDate());
        if (dto.getApplicationId() != null) {
            var application = applicationRepository.getById(dto.getApplicationId());
            if (application == null || !Boolean.TRUE.equals(application.getIsActive())) throw new ResourceNotFoundException("Application not found or inactive: " + dto.getApplicationId());
            entity.setApplication(application);
        }
        if (dto.getProjectId() != null) {
            var project = projectRepository.getById(dto.getProjectId());
            if (project == null || !Boolean.TRUE.equals(project.getIsActive())) throw new ResourceNotFoundException("Project not found or inactive: " + dto.getProjectId());
            entity.setProject(project);
        }
        repository.save(entity);
        return entity.getId();
    }

    public List<Document> getAll() { return repository.getAllDocument(); }

    public Document getById(Long id) {
        Optional<Document> entity = repository.findById(id);
        if (entity.isEmpty() || !Boolean.TRUE.equals(entity.get().getIsActive())) throw new ResourceNotFoundException("Document not found by id: " + id);
        return entity.get();
    }

    public Document update(DocumentDTO dto) {
        Document entity = getById(dto.getDocumentId());
        entity.setUpdatedDate(new Date());
        entity.setTitle(dto.getTitle());
        entity.setType(dto.getType());
        entity.setUploadDate(dto.getUploadDate());
        if (dto.getApplicationId() != null) {
            var application = applicationRepository.getById(dto.getApplicationId());
            if (application == null || !Boolean.TRUE.equals(application.getIsActive())) throw new ResourceNotFoundException("Application not found or inactive: " + dto.getApplicationId());
            entity.setApplication(application);
        }
        if (dto.getProjectId() != null) {
            var project = projectRepository.getById(dto.getProjectId());
            if (project == null || !Boolean.TRUE.equals(project.getIsActive())) throw new ResourceNotFoundException("Project not found or inactive: " + dto.getProjectId());
            entity.setProject(project);
        }
        return repository.save(entity);
    }

    public Boolean deleteById(Long id) {
        Document entity = getById(id);
        entity.setIsActive(false);
        entity.setUpdatedDate(new Date());
        repository.save(entity);
        return true;
    }
}
