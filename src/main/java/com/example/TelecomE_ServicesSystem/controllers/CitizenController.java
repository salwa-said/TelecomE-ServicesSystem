package com.example.TelecomE_ServicesSystem.controllers;

import com.example.TelecomE_ServicesSystem.dto.CitizenDTO;
import com.example.TelecomE_ServicesSystem.entites.Citizen;
import com.example.TelecomE_ServicesSystem.services.CitizenService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("citizen")
public class CitizenController {
    private CitizenService service;

    @Autowired
    public CitizenController(CitizenService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Long add(@Valid @RequestBody CitizenDTO dto) {
        return service.create(dto);
    }

    @GetMapping("getAll")
    public List<CitizenDTO> getAll() {
        return CitizenDTO.convertToDTO(service.getAll());
    }

    @GetMapping("getById")
    public CitizenDTO getById(@RequestParam Long id) {
        return CitizenDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("update")
    public CitizenDTO update(@Valid @RequestBody CitizenDTO dto) {
        return CitizenDTO.convertToDTO(service.update(dto));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteById(@RequestParam Long id) {
        return service.deleteById(id);
    }
}

