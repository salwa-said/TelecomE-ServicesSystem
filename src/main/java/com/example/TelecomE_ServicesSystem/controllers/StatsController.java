package com.example.TelecomE_ServicesSystem.controllers;
import com.example.TelecomE_ServicesSystem.services.*;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("stats")
public class StatsController {
    private DepartmentStatsService departmentStats;
    private OperatorStatsService operatorStats;

    public StatsController(DepartmentStatsService d, OperatorStatsService o) {
        departmentStats = d;
        operatorStats = o;
    }

    @GetMapping("department")
    public Map<String, Object> department(@RequestParam Long departmentId) {
        return departmentStats.stats(departmentId);
    }

    @GetMapping("operator")
    public Map<String, Object> operator(@RequestParam Long operatorId) {
        return operatorStats.stats(operatorId);
    }
}