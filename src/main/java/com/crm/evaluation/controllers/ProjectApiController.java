package com.crm.evaluation.controllers;

import com.crm.evaluation.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/projects")
public class ProjectApiController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/chart")
    public Map<String, Integer> getProjectCountByStatus() throws Exception {
        return dashboardService.getProjectCountByStatus().getDataInteger();
    }
}
