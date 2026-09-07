package com.example.TelecomE_ServicesSystem.controllers;
import com.example.TelecomE_ServicesSystem.dto.PaymentDTO;
import com.example.TelecomE_ServicesSystem.entites.Payment;
import com.example.TelecomE_ServicesSystem.services.PaymentService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("payment")
public class PaymentController {
    private PaymentService service;

    @Autowired
    public PaymentController(PaymentService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Long add(@Valid @RequestBody PaymentDTO dto) {
        return service.create(dto);
    }

    @GetMapping("getAll")
    public List<PaymentDTO> getAll() {
        return PaymentDTO.convertToDTO(service.getAll());
    }

    @GetMapping("getById")
    public PaymentDTO getById(@RequestParam Long id) {
        return PaymentDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("update")
    public PaymentDTO update(@Valid @RequestBody PaymentDTO dto) {
        return PaymentDTO.convertToDTO(service.update(dto));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteById(@RequestParam Long id) {
        return service.deleteById(id);
    }
}

