package com.example.TelecomE_ServicesSystem.controllers;
import com.example.TelecomE_ServicesSystem.dto.DocumentDTO;
import com.example.TelecomE_ServicesSystem.entites.Document;
import com.example.TelecomE_ServicesSystem.services.DocumentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("document")
public class DocumentController {
    private DocumentService service;

    @Autowired
    public DocumentController(DocumentService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Long add(@Valid @RequestBody DocumentDTO dto) {
        return service.create(dto);
    }

    @GetMapping("getAll")
    public List<DocumentDTO> getAll() {
        return DocumentDTO.convertToDTO(service.getAll());
    }

    @GetMapping("getById")
    public DocumentDTO getById(@RequestParam Long id) {
        return DocumentDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("update")
    public DocumentDTO update(@Valid @RequestBody DocumentDTO dto) {
        return DocumentDTO.convertToDTO(service.update(dto));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteById(@RequestParam Long id) {
        return service.deleteById(id);
    }
}

