package com.crm.evaluation.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.crm.evaluation.services.DashboardService;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceApiController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping("/chart/{annee}/{mois}")
    public Map<String, Double> getInvoicePaymentSummary(@PathVariable(required = false) String annee, @PathVariable(required = false)   String mois) throws Exception {
        return dashboardService.getInvoicePaymentSummary(annee, mois).getDataDouble();
    }

    @GetMapping("/chart")
    public Map<String, Double> getInvoicePaymentSummary2() throws Exception {
        return dashboardService.getInvoicePaymentSummary(null, null).getDataDouble();
    }
}