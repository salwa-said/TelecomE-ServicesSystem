package com.example.TelecomE_ServicesSystem.controllers;
import com.example.TelecomE_ServicesSystem.dto.ApplicationDTO;
import com.example.TelecomE_ServicesSystem.entites.Application;
import com.example.TelecomE_ServicesSystem.services.ApplicationService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("application")
public class ApplicationController {
    private ApplicationService service;

    @Autowired
    public ApplicationController(ApplicationService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Long add(@Valid @RequestBody ApplicationDTO dto) {
        return service.create(dto);
    }

    @GetMapping("getAll")
    public List<ApplicationDTO> getAll() {
        return ApplicationDTO.convertToDTO(service.getAll());
    }

    @GetMapping("getById")
    public ApplicationDTO getById(@RequestParam Long id) {
        return ApplicationDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("update")
    public ApplicationDTO update(@Valid @RequestBody ApplicationDTO dto) {
        return ApplicationDTO.convertToDTO(service.update(dto));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteById(@RequestParam Long id) {
        return service.deleteById(id);
    }
}
