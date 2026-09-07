package com.example.TelecomE_ServicesSystem.services;
import com.example.TelecomE_ServicesSystem.dto.*;
import com.example.TelecomE_ServicesSystem.exceptions.*;
import com.example.TelecomE_ServicesSystem.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.TelecomE_ServicesSystem.entites.*;
import java.time.LocalDate;
import java.util.*;

@org.springframework.stereotype.Service
public class ApplicationService {
    private ApplicationRepository repository;
    private CitizenRepository citizenRepository;
    private ServiceRepository serviceRepository;
    private OfficerRepository officerRepository;
    private DocumentRepository documentRepository;

    @Autowired
    public ApplicationService(ApplicationRepository repository, CitizenRepository citizenRepository, ServiceRepository serviceRepository, OfficerRepository officerRepository, DocumentRepository documentRepository) {
        this.repository = repository;
        this.citizenRepository = citizenRepository;
        this.serviceRepository = serviceRepository;
        this.officerRepository = officerRepository;
        this.documentRepository = documentRepository;
    }

    public Long create(ApplicationDTO dto) {
        Application e = new Application();
        e.setIsActive(true);
        e.setCreatedDate(new Date());
        e.setApplicationDate(dto.getApplicationDate());
        e.setStatus(dto.getStatus());
        e.setReferenceNumber(dto.getReferenceNumber());
        if (dto.getCitizenId() != null) e.setCitizen(citizenRepository.getById(dto.getCitizenId()));
        if (dto.getServiceId() != null) e.setService(serviceRepository.getById(dto.getServiceId()));
        if (dto.getOfficerId() != null) e.setOfficer(officerRepository.getById(dto.getOfficerId()));
        return repository.save(e).getId();
    }

    public List<Application> getAll() {
        return repository.getAllApplication();
    }

    public Application getById(Long id) {
        Optional<Application> e = repository.findById(id);
        if (e.isEmpty() || !Boolean.TRUE.equals(e.get().getIsActive()))
            throw new ResourceNotFoundException("Application not found by id: " + id);
        return e.get();
    }

    public Application update(ApplicationDTO dto) {
        Application e = getById(dto.getApplicationId());
        e.setUpdatedDate(new Date());
        e.setApplicationDate(dto.getApplicationDate());
        e.setStatus(dto.getStatus());
        e.setReferenceNumber(dto.getReferenceNumber());
        if (dto.getCitizenId() != null) e.setCitizen(citizenRepository.getById(dto.getCitizenId()));
        if (dto.getServiceId() != null) e.setService(serviceRepository.getById(dto.getServiceId()));
        if (dto.getOfficerId() != null) e.setOfficer(officerRepository.getById(dto.getOfficerId()));
        return repository.save(e);
    }

    public Boolean deleteById(Long id) {
        Application e = getById(id);
        e.setIsActive(false);
        e.setUpdatedDate(new Date());
        repository.save(e);
        return true;
    }

    public Application submit(ApplicationDTO dto) {
        Citizen c = citizenRepository.getById(dto.getCitizenId());
        if (c == null || !Boolean.TRUE.equals(c.getIsActive()))
            throw new ResourceNotFoundException("Citizen not found or inactive: " + dto.getCitizenId());
        com.example.TelecomE_ServicesSystem.entites.Service s = serviceRepository.getById(dto.getServiceId());
        if (s == null || !Boolean.TRUE.equals(s.getIsActive()))
            throw new ResourceNotFoundException("Service not found or inactive: " + dto.getServiceId());
        List<Officer> officers = officerRepository.getAllOfficer();
        Officer chosen = officers.stream().filter(o -> o.getDepartment() != null && s.getDepartment() != null && o.getDepartment().getId().equals(s.getDepartment().getId())).findFirst().orElse(officers.stream().findFirst().orElse(null));
        if (chosen == null) throw new BusinessException("No active officer is available to handle the application");
        Application a = new Application();
        a.setIsActive(true);
        a.setCreatedDate(new Date());
        a.setApplicationDate(LocalDate.now());
        a.setStatus("submitted");
        a.setReferenceNumber("APP-" + System.currentTimeMillis());
        a.setCitizen(c);
        a.setService(s);
        a.setOfficer(chosen);
        return repository.save(a);
    }

    public Application recordPayment(PaymentDTO dto, Long applicationId) {
        Application a = getById(applicationId);
        if (a.getPayment() != null && a.getPayment().getIsActive() != null && a.getPayment().getIsActive())
            throw new BusinessException("Application has already been paid");
        Payment p = new Payment();
        p.setIsActive(true);
        p.setCreatedDate(new Date());
        p.setAmount(dto.getAmount());
        p.setMethod(dto.getMethod());
        p.setStatus("paid");
        p.setPaidDate(LocalDate.now());
        p.setApplication(a);
        a.setPayment(p);
        if (!"processing".equalsIgnoreCase(a.getStatus())) a.setStatus("processing");
        repository.save(a);
        return a;
    }

    public Application decide(Long id, Long officerId, String decision, String documentTitle, String documentType) {
        Application a = getById(id);
        Officer o = officerRepository.getById(officerId);
        if (o == null || !Boolean.TRUE.equals(o.getIsActive()))
            throw new ResourceNotFoundException("Officer not found or inactive: " + officerId);
        if (a.getOfficer() == null || !a.getOfficer().getId().equals(o.getId()))
            throw new BusinessException("Only the assigned officer can decide this application");
        if (a.getPayment() == null || !Boolean.TRUE.equals(a.getPayment().getIsActive()) || !"paid".equalsIgnoreCase(a.getPayment().getStatus()))
            throw new BusinessException("Application must be paid before decision");
        if (decision == null || (!decision.equalsIgnoreCase("approve") && !decision.equalsIgnoreCase("reject")))
            throw new BusinessException("Decision must be approve or reject");
        if (documentTitle == null || documentTitle.isBlank()) throw new BusinessException("Document title is required");
        if (documentType == null || documentType.isBlank()) throw new BusinessException("Document type is required");
        String d = decision.trim().toLowerCase();
        a.setStatus(d.equals("approve") ? "approved" : "rejected");
        Document doc = new Document();
        doc.setIsActive(true);
        doc.setCreatedDate(new Date());
        doc.setTitle(documentTitle);
        doc.setType(documentType);
        doc.setUploadDate(LocalDate.now());
        doc.setApplication(a);
        documentRepository.save(doc);
        return repository.save(a);
    }

    public List<Application> findByStatus(String status) {
        return repository.findByStatus(status);
    }

    public List<Application> findByCitizen(Long citizenId) {
        return repository.findByCitizen(citizenId);
    }
}
