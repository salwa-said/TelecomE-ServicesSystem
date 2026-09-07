package com.example.TelecomE_ServicesSystem.controllers;
import com.example.TelecomE_ServicesSystem.dto.DomainRegistrationDTO;
import com.example.TelecomE_ServicesSystem.entites.DomainRegistration;
import com.example.TelecomE_ServicesSystem.services.DomainRegistrationService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("domainRegistration")
public class DomainRegistrationController {
    private DomainRegistrationService service;

    @Autowired
    public DomainRegistrationController(DomainRegistrationService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Long add(@Valid @RequestBody DomainRegistrationDTO dto) {
        return service.create(dto);
    }

    @GetMapping("getAll")
    public List<DomainRegistrationDTO> getAll() {
        return DomainRegistrationDTO.convertToDTO(service.getAll());
    }

    @GetMapping("getById")
    public DomainRegistrationDTO getById(@RequestParam Long id) {
        return DomainRegistrationDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("update")
    public DomainRegistrationDTO update(@Valid @RequestBody DomainRegistrationDTO dto) {
        return DomainRegistrationDTO.convertToDTO(service.update(dto));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteById(@RequestParam Long id) {
        return service.deleteById(id);
    }
}
