package com.example.TelecomE_ServicesSystem.controllers;
import com.example.TelecomE_ServicesSystem.dto.ApplicationDTO;
import com.example.TelecomE_ServicesSystem.dto.PaymentDTO;
import com.example.TelecomE_ServicesSystem.services.ApplicationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("application")
public class ApplicationBusinessController {
    private ApplicationService service;

    public ApplicationBusinessController(ApplicationService service){ this.service=service; }

    @PostMapping("submit")
    public ApplicationDTO submit(@Valid @RequestBody ApplicationDTO dto){
        return ApplicationDTO.convertToDTO(service.submit(dto));
    }

    @PostMapping("{id}/payment")
    public ApplicationDTO payment(@PathVariable Long id, @Valid @RequestBody PaymentDTO dto){
        return ApplicationDTO.convertToDTO(service.recordPayment(dto, id));
    }

    @PostMapping("{id}/decision")
    public ApplicationDTO decide(@PathVariable Long id,
                                 @RequestParam Long officerId,
                                 @RequestParam String decision,
                                 @RequestParam String documentTitle,
                                 @RequestParam String documentType){
        return ApplicationDTO.convertToDTO(service.decide(id, officerId, decision, documentTitle, documentType));
    }

    @GetMapping("byStatus")
    public List<ApplicationDTO> byStatus(@RequestParam String status){
        return ApplicationDTO.convertToDTO(service.findByStatus(status));
    }

    @GetMapping("citizenHistory")
    public List<ApplicationDTO> citizenHistory(@RequestParam Long citizenId){
        return ApplicationDTO.convertToDTO(service.findByCitizen(citizenId));
    }
}
