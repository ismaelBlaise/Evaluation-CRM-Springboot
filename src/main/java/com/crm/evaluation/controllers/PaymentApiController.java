package com.crm.evaluation.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crm.evaluation.dtos.PaymentResponseDTO;
import com.crm.evaluation.services.DashboardService;
import com.crm.evaluation.services.PaymentService;

@RestController
@RequestMapping("/api/payments")
public class PaymentApiController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/chart")
    public Map<String, Integer> getMonthlyRevenueChart() throws Exception {
        return dashboardService.getMonthlyRevenueChart();
    }

    
}
