package com.crm.evaluation.controllers;

import com.crm.evaluation.services.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @GetMapping
    public ModelAndView getInvoices(@RequestParam(value = "page", defaultValue = "1") int page,
                                    @RequestParam(value = "per_page", defaultValue = "10") int perPage) {
        ModelAndView modelAndView = new ModelAndView("template");
        modelAndView.addObject("page", "invoices/invoices");
        modelAndView.addObject("invoices", invoiceService.getInvoices(page, perPage));
        return modelAndView;
    }
}
