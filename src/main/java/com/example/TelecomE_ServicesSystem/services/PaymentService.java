package com.example.TelecomE_ServicesSystem.services;
import com.example.TelecomE_ServicesSystem.entites.*;
import com.example.TelecomE_ServicesSystem.dto.*;
import com.example.TelecomE_ServicesSystem.repositories.*;
import com.example.TelecomE_ServicesSystem.exceptions.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.*;

@org.springframework.stereotype.Service
public class PaymentService {
    private PaymentRepository repository;
    private ApplicationRepository applicationRepository;

    @Autowired
    public PaymentService(PaymentRepository repository, ApplicationRepository applicationRepository) {
        this.repository = repository;
        this.applicationRepository = applicationRepository;
    }

    public Long create(PaymentDTO dto) {
        Application a = applicationRepository.getById(dto.getApplicationId());
        if (a == null) throw new ResourceNotFoundException("Application not found: " + dto.getApplicationId());
        if (a.getPayment() != null) throw new BusinessException("Application already has a payment");
        Payment e = new Payment();
        e.setIsActive(true);
        e.setCreatedDate(new Date());
        e.setAmount(dto.getAmount());
        e.setMethod(dto.getMethod());
        e.setStatus(dto.getStatus());
        e.setPaidDate(dto.getPaidDate());
        e.setApplication(a);
        return repository.save(e).getId();
    }

    public List<Payment> getAll() {
        return repository.getAllPayment();
    }

    public Payment getById(Long id) {
        Optional<Payment> e = repository.findById(id);
        if (e.isEmpty() || !Boolean.TRUE.equals(e.get().getIsActive()))
            throw new ResourceNotFoundException("Payment not found by id: " + id);
        return e.get();
    }

    public Payment update(PaymentDTO dto) {
        Payment e = getById(dto.getPaymentId());
        e.setUpdatedDate(new Date());
        e.setAmount(dto.getAmount());
        e.setMethod(dto.getMethod());
        e.setStatus(dto.getStatus());
        e.setPaidDate(dto.getPaidDate());
        return repository.save(e);
    }

    public Boolean deleteById(Long id) {
        Payment e = getById(id);
        e.setIsActive(false);
        e.setUpdatedDate(new Date());
        repository.save(e);
        return true;
    }
}