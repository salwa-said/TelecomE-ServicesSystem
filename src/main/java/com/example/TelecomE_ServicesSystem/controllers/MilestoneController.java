package com.example.TelecomE_ServicesSystem.controllers;
import com.example.TelecomE_ServicesSystem.dto.MilestoneDTO;
import com.example.TelecomE_ServicesSystem.entites.Milestone;
import com.example.TelecomE_ServicesSystem.services.MilestoneService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("milestone")
public class MilestoneController {
    private MilestoneService service;

    @Autowired
    public MilestoneController(MilestoneService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Long add(@Valid @RequestBody MilestoneDTO dto) {
        return service.create(dto);
    }

    @GetMapping("getAll")
    public List<MilestoneDTO> getAll() {
        return MilestoneDTO.convertToDTO(service.getAll());
    }

    @GetMapping("getById")
    public MilestoneDTO getById(@RequestParam Long id) {
        return MilestoneDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("update")
    public MilestoneDTO update(@Valid @RequestBody MilestoneDTO dto) {
        return MilestoneDTO.convertToDTO(service.update(dto));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteById(@RequestParam Long id) {
        return service.deleteById(id);
    }
}

