package com.example.TelecomE_ServicesSystem.controllers;
import com.example.TelecomE_ServicesSystem.dto.ProjectDTO;
import com.example.TelecomE_ServicesSystem.entites.Project;
import com.example.TelecomE_ServicesSystem.services.ProjectService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("project")
public class ProjectController {
    private ProjectService service;

    @Autowired
    public ProjectController(ProjectService service) {
        this.service = service;
    }

    @PostMapping("add")
    public Long add(@Valid @RequestBody ProjectDTO dto) {
        return service.create(dto);
    }

    @GetMapping("getAll")
    public List<ProjectDTO> getAll() {
        return ProjectDTO.convertToDTO(service.getAll());
    }

    @GetMapping("getById")
    public ProjectDTO getById(@RequestParam Long id) {
        return ProjectDTO.convertToDTO(service.getById(id));
    }

    @PutMapping("update")
    public ProjectDTO update(@Valid @RequestBody ProjectDTO dto) {
        return ProjectDTO.convertToDTO(service.update(dto));
    }

    @DeleteMapping("deleteById")
    public Boolean deleteById(@RequestParam Long id) {
        return service.deleteById(id);
    }
}
