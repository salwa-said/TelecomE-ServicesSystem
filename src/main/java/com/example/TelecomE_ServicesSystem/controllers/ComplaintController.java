package com.example.TelecomE_ServicesSystem.controllers;
import com.example.TelecomE_ServicesSystem.dto.ComplaintDTO;
import com.example.TelecomE_ServicesSystem.entites.Complaint;
import com.example.TelecomE_ServicesSystem.services.ComplaintService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("complaint")
public class ComplaintController {
    private ComplaintService service;

    @Autowired
    public ComplaintController(ComplaintService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Long add(@Valid @RequestBody ComplaintDTO dto) {
        return service.create(dto);
    }

    @GetMapping("getAll")
    public List<ComplaintDTO> getAll() {
        return ComplaintDTO.convertToDTO(service.getAll());
    }

    @GetMapping("getById")
    public ComplaintDTO getById(@RequestParam Long id) {
        return ComplaintDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("update")
    public ComplaintDTO update(@Valid @RequestBody ComplaintDTO dto) {
        return ComplaintDTO.convertToDTO(service.update(dto));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteById(@RequestParam Long id) {
        return service.deleteById(id);
    }
}

