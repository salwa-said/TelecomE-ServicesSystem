package com.example.TelecomE_ServicesSystem.controllers;

import com.example.TelecomE_ServicesSystem.dto.SpectrumLicenseDTO;
import com.example.TelecomE_ServicesSystem.entites.SpectrumLicense;
import com.example.TelecomE_ServicesSystem.services.SpectrumLicenseService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("spectrumLicense")
public class SpectrumLicenseController {
    private SpectrumLicenseService service;

    @Autowired
    public SpectrumLicenseController(SpectrumLicenseService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Long add(@Valid @RequestBody SpectrumLicenseDTO dto) {
        return service.create(dto);
    }

    @GetMapping("getAll")
    public List<SpectrumLicenseDTO> getAll() {
        return SpectrumLicenseDTO.convertToDTO(service.getAll());
    }

    @GetMapping("getById")
    public SpectrumLicenseDTO getById(@RequestParam Long id) {
        return SpectrumLicenseDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("update")
    public SpectrumLicenseDTO update(@Valid @RequestBody SpectrumLicenseDTO dto) {
        return SpectrumLicenseDTO.convertToDTO(service.update(dto));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteById(@RequestParam Long id) {
        return service.deleteById(id);
    }
}

