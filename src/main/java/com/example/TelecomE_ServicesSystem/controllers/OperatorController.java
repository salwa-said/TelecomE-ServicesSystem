package com.example.TelecomE_ServicesSystem.controllers;

import com.example.TelecomE_ServicesSystem.dto.OperatorDTO;
import com.example.TelecomE_ServicesSystem.entites.Operator;
import com.example.TelecomE_ServicesSystem.services.OperatorService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("operator")

public class OperatorController {
    private OperatorService service;

    @Autowired
    public OperatorController(OperatorService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Long add(@Valid @RequestBody OperatorDTO dto) {
        return service.create(dto);
    }

    @GetMapping("getAll")
    public List<OperatorDTO> getAll() {
        return OperatorDTO.convertToDTO(service.getAll());
    }

    @GetMapping("getById")
    public OperatorDTO getById(@RequestParam Long id) {
        return OperatorDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("update")
    public OperatorDTO update(@Valid @RequestBody OperatorDTO dto) {
        return OperatorDTO.convertToDTO(service.update(dto));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteById(@RequestParam Long id) {
        return service.deleteById(id);
    }
}

