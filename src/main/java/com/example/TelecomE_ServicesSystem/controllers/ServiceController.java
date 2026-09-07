package com.example.TelecomE_ServicesSystem.controllers;

import com.example.TelecomE_ServicesSystem.dto.ServiceDTO;
import com.example.TelecomE_ServicesSystem.services.ServiceService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("service")
public class ServiceController {
    private ServiceService service;

    @Autowired
    public ServiceController(ServiceService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Long add(@Valid @RequestBody ServiceDTO dto) {
        return service.create(dto);
    }

    @GetMapping("getAll")
    public List<ServiceDTO> getAll() {
        return ServiceDTO.convertToDTO(service.getAll());
    }

    @GetMapping("getById")
    public ServiceDTO getById(@RequestParam Long id) {
        return ServiceDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("update")
    public ServiceDTO update(@Valid @RequestBody ServiceDTO dto) {
        return ServiceDTO.convertToDTO(service.update(dto));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteById(@RequestParam Long id) {
        return service.deleteById(id);
    }
}
