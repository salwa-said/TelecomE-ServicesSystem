package com.example.TelecomE_ServicesSystem.controllers;

import com.example.TelecomE_ServicesSystem.dto.SpectrumLicenseDTO;
import com.example.TelecomE_ServicesSystem.services.SpectrumLicenseService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("spectrumLicense")
public class SpectrumLicenseBusinessController {
    private SpectrumLicenseService service;

    public SpectrumLicenseBusinessController(SpectrumLicenseService service) {
        this.service = service;
    }

    @GetMapping("expiringSoon")
    public List<SpectrumLicenseDTO> expiringSoon() {
        return SpectrumLicenseDTO.convertToDTO(service.expiringSoon());
    }
}