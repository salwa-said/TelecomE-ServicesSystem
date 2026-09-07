package com.example.TelecomE_ServicesSystem.controllers;

import com.example.TelecomE_ServicesSystem.dto.OfficerDTO;
import com.example.TelecomE_ServicesSystem.entites.Officer;
import com.example.TelecomE_ServicesSystem.services.OfficerService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("officer")
public class OfficerController {
    private OfficerService service;

    @Autowired
    public OfficerController(OfficerService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Long add(@Valid @RequestBody OfficerDTO dto) {
        return service.create(dto);
    }

    @GetMapping("getAll")
    public List<OfficerDTO> getAll() {
        return OfficerDTO.convertToDTO(service.getAll());
    }

    @GetMapping("getById")
    public OfficerDTO getById(@RequestParam Long id) {
        return OfficerDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("update")
    public OfficerDTO update(@Valid @RequestBody OfficerDTO dto) {
        return OfficerDTO.convertToDTO(service.update(dto));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteById(@RequestParam Long id) {
        return service.deleteById(id);
    }
}
