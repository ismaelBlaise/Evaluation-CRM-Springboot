package com.crm.evaluation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.crm.evaluation.services.PaymentService;

@Controller
@RequestMapping("/payments")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @GetMapping
    public ModelAndView getPayments( @RequestParam(value = "page", defaultValue = "1") int page,@RequestParam(value = "per_page", defaultValue = "10") int perPage) throws Exception {
        ModelAndView modelAndView=new ModelAndView("template");
        modelAndView.addObject("page","payments/payments");
        modelAndView.addObject("payments",paymentService.getPayments(page, perPage));
        return modelAndView;
    }
}
