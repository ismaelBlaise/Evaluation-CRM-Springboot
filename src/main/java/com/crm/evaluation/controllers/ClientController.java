package com.crm.evaluation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.crm.evaluation.services.ClientService;
import com.crm.evaluation.services.PaymentService;

@Controller
@RequestMapping("/clients")
public class ClientController {
    @Autowired
    private ClientService clientService;

    @GetMapping
    public ModelAndView getClients( @RequestParam(value = "page", defaultValue = "1") int page,@RequestParam(value = "per_page", defaultValue = "10") int perPage) throws Exception {
        ModelAndView modelAndView=new ModelAndView("template");
        modelAndView.addObject("page","clients/clients");
        modelAndView.addObject("clients",clientService.getClients(page, perPage));
        return modelAndView;
    }

    
}
