package com.example.TelecomE_ServicesSystem.services;

import com.example.TelecomE_ServicesSystem.repositories.*;
import com.example.TelecomE_ServicesSystem.exceptions.*;

import java.util.*;

@org.springframework.stereotype.Service
public class OperatorStatsService {
    private OperatorRepository operatorRepository;
    private SpectrumLicenseRepository licenseRepository;
    private ComplaintRepository complaintRepository;

    public OperatorStatsService(OperatorRepository o, SpectrumLicenseRepository l, ComplaintRepository c) {
        operatorRepository = o;
        licenseRepository = l;
        complaintRepository = c;
    }

    public Map<String, Object> stats(Long id) {
        var o = operatorRepository.getById(id);
        if (o == null || !Boolean.TRUE.equals(o.getIsActive()))
            throw new ResourceNotFoundException("Operator not found or inactive: " + id);
        long active = licenseRepository.getAllSpectrumLicense().stream().filter(x -> x.getOperator() != null && x.getOperator().getId().equals(id) && "active".equalsIgnoreCase(x.getStatus())).count();
        long open = complaintRepository.findOpenByOperator(id).size();
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("operatorId", id);
        m.put("operatorName", o.getName());
        m.put("activeLicenses", active);
        m.put("openComplaints", open);
        return m;
    }
}
