package com.example.TelecomE_ServicesSystem.controllers;

import com.example.TelecomE_ServicesSystem.dto.InspectionDTO;
import com.example.TelecomE_ServicesSystem.entites.Inspection;
import com.example.TelecomE_ServicesSystem.services.InspectionService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("inspection")
public class InspectionController {
    private InspectionService service;

    @Autowired
    public InspectionController(InspectionService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Long add(@Valid @RequestBody InspectionDTO dto) {
        return service.create(dto);
    }

    @GetMapping("getAll")
    public List<InspectionDTO> getAll() {
        return InspectionDTO.convertToDTO(service.getAll());
    }

    @GetMapping("getById")
    public InspectionDTO getById(@RequestParam Long id) {
        return InspectionDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("update")
    public InspectionDTO update(@Valid @RequestBody InspectionDTO dto) {
        return InspectionDTO.convertToDTO(service.update(dto));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteById(@RequestParam Long id) {
        return service.deleteById(id);
    }
}
