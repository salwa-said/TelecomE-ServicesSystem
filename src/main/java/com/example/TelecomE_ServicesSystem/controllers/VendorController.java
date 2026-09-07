package com.example.TelecomE_ServicesSystem.controllers;
import com.example.TelecomE_ServicesSystem.dto.VendorDTO;
import com.example.TelecomE_ServicesSystem.entites.Vendor;
import com.example.TelecomE_ServicesSystem.services.VendorService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("vendor")
public class VendorController {
    private VendorService service;

    @Autowired
    public VendorController(VendorService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Long add(@Valid @RequestBody VendorDTO dto) {
        return service.create(dto);
    }

    @GetMapping("getAll")
    public List<VendorDTO> getAll() {
        return VendorDTO.convertToDTO(service.getAll());
    }

    @GetMapping("getById")
    public VendorDTO getById(@RequestParam Long id) {
        return VendorDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("update")
    public VendorDTO update(@Valid @RequestBody VendorDTO dto) {
        return VendorDTO.convertToDTO(service.update(dto));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteById(@RequestParam Long id) {
        return service.deleteById(id);
    }
}
