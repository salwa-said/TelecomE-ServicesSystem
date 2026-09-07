package com.example.TelecomE_ServicesSystem.controllers;
import com.example.TelecomE_ServicesSystem.dto.*;
import com.example.TelecomE_ServicesSystem.services.ProjectService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("project")
public class ProjectBusinessController {
    private ProjectService service;

    public ProjectBusinessController(ProjectService service) {
        this.service = service;
    }

    @GetMapping("overBudget")
    public List<ProjectDTO> overBudget(@RequestParam BigDecimal threshold) {
        return ProjectDTO.convertToDTO(service.overBudget(threshold));
    }

    @PostMapping("{id}/milestone")
    public ProjectDTO addMilestone(@PathVariable Long id, @Valid @RequestBody MilestoneDTO dto) {
        return ProjectDTO.convertToDTO(service.addMilestone(id, dto));
    }

    @PutMapping("milestone/{milestoneId}/complete")
    public MilestoneDTO complete(@PathVariable Long milestoneId) {
        return MilestoneDTO.convertToDTO(service.completeMilestone(milestoneId));
    }

    @GetMapping("{id}/stats")
    public Map<String, Object> stats(@PathVariable Long id) {
        return service.stats(id);
    }
}

