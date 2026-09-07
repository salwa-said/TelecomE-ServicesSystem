package com.example.TelecomE_ServicesSystem.controllers;
import com.example.TelecomE_ServicesSystem.dto.DepartmentDTO;
import com.example.TelecomE_ServicesSystem.entites.Department;
import com.example.TelecomE_ServicesSystem.services.DepartmentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("department")
public class DepartmentController {
    private DepartmentService service;

    @Autowired
    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Long add(@Valid @RequestBody DepartmentDTO dto) {
        return service.create(dto);
    }

    @GetMapping("getAll")
    public List<DepartmentDTO> getAll() {
        return DepartmentDTO.convertToDTO(service.getAll());
    }

    @GetMapping("getById")
    public DepartmentDTO getById(@RequestParam Long id) {
        return DepartmentDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("update")
    public DepartmentDTO update(@Valid @RequestBody DepartmentDTO dto) {
        return DepartmentDTO.convertToDTO(service.update(dto));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteById(@RequestParam Long id) {
        return service.deleteById(id);
    }
}

