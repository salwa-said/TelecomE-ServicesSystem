package com.example.TelecomE_ServicesSystem.controllers;

import com.example.TelecomE_ServicesSystem.dto.MinistryDTO;
import com.example.TelecomE_ServicesSystem.entites.Ministry;
import com.example.TelecomE_ServicesSystem.services.MinistryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("ministry")
public class MinistryController {
    private MinistryService service;

    @Autowired
    public MinistryController(MinistryService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Long add(@Valid @RequestBody MinistryDTO dto) {
        return service.create(dto);
    }

    @GetMapping("getAll")
    public List<MinistryDTO> getAll() {
        return MinistryDTO.convertToDTO(service.getAll());
    }

    @GetMapping("getById")
    public MinistryDTO getById(@RequestParam Long id) {
        return MinistryDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("update")
    public MinistryDTO update(@Valid @RequestBody MinistryDTO dto) {
        return MinistryDTO.convertToDTO(service.update(dto));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteById(@RequestParam Long id) {
        return service.deleteById(id);
    }
}

