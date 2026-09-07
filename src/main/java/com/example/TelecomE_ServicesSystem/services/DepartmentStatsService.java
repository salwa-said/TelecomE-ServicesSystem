package com.example.TelecomE_ServicesSystem.services;

import com.example.TelecomE_ServicesSystem.repositories.*;
import com.example.TelecomE_ServicesSystem.exceptions.*;

import java.util.*;

@org.springframework.stereotype.Service
public class DepartmentStatsService {
    private DepartmentRepository departmentRepository;
    private ApplicationRepository applicationRepository;
    private OfficerRepository officerRepository;

    public DepartmentStatsService(DepartmentRepository d, ApplicationRepository a, OfficerRepository o) {
        departmentRepository = d;
        applicationRepository = a;
        officerRepository = o;
    }

    public Map<String, Object> stats(Long departmentId) {
        var d = departmentRepository.getById(departmentId);
        if (d == null || !Boolean.TRUE.equals(d.getIsActive()))
            throw new ResourceNotFoundException("Department not found or inactive: " + departmentId);
        long pending = applicationRepository.getAllApplication().stream().filter(a -> a.getService() != null && a.getService().getDepartment() != null && a.getService().getDepartment().getId().equals(departmentId) && "pending".equalsIgnoreCase(a.getStatus())).count();
        long workload = officerRepository.getAllOfficer().stream().filter(o -> o.getDepartment() != null && o.getDepartment().getId().equals(departmentId)).mapToLong(o -> o.getApplications() == null ? 0 : o.getApplications().stream().filter(a -> a.getIsActive() != null && a.getIsActive()).count()).sum();
        Map<String, Object> m = new LinkedHashMap<>();
        m.put("departmentId", departmentId);
        m.put("departmentName", d.getName());
        m.put("pendingApplications", pending);
        m.put("officerWorkload", workload);
        return m;
    }
}

