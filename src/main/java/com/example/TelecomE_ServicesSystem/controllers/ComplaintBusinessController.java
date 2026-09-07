package com.example.TelecomE_ServicesSystem.controllers;
import com.example.TelecomE_ServicesSystem.dto.ComplaintDTO;
import com.example.TelecomE_ServicesSystem.services.ComplaintService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("complaint")
public class ComplaintBusinessController {
    private final ComplaintService service;

    public ComplaintBusinessController(ComplaintService service) {
        this.service = service;
    }

    @PostMapping("file")
    public ComplaintDTO file(@Valid @RequestBody ComplaintDTO dto) {
        return ComplaintDTO.convertToDTO(service.file(dto));
    }

    @PutMapping("{id}/assign")
    public ComplaintDTO assign(@PathVariable Long id, @RequestParam Long officerId) {
        return ComplaintDTO.convertToDTO(service.assign(id, officerId));
    }

    @PutMapping("{id}/resolve")
    public ComplaintDTO resolve(@PathVariable Long id) {
        return ComplaintDTO.convertToDTO(service.resolve(id));
    }

    @GetMapping("open")
    public List<ComplaintDTO> open() {
        return ComplaintDTO.convertToDTO(service.open());
    }

    @GetMapping("operatorOpen")
    public List<ComplaintDTO> operatorOpen(@RequestParam Long operatorId) {
        return ComplaintDTO.convertToDTO(service.openForOperator(operatorId));
    }
}

