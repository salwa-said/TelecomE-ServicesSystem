package com.example.TelecomE_ServicesSystem.controllers;

import com.example.TelecomE_ServicesSystem.dto.DomainRegistrationDTO;
import com.example.TelecomE_ServicesSystem.services.DomainRegistrationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("domainRegistration")
public class DomainBusinessController {
    private final DomainRegistrationService service;

    public DomainBusinessController(DomainRegistrationService service) {
        this.service = service;
    }

    @PostMapping("{id}/renew")
    public DomainRegistrationDTO renew(@PathVariable Long id, @RequestParam java.time.LocalDate newExpiryDate) {
        return DomainRegistrationDTO.convertToDTO(service.renew(id, newExpiryDate));
    }
}

