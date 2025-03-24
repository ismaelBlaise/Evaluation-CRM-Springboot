package com.crm.evaluation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.crm.evaluation.services.OfferService;

@Controller
@RequestMapping("/offers")
public class OfferController {

    @Autowired
    private OfferService offerService;

    @GetMapping
    public ModelAndView getOffers(@RequestParam(value = "page", defaultValue = "1") int page, 
                                  @RequestParam(value = "per_page", defaultValue = "10") int perPage) throws Exception {
        ModelAndView modelAndView = new ModelAndView("template");
        modelAndView.addObject("page", "offers/offers");  // The page to display the offers
        modelAndView.addObject("offers", offerService.getOffers(page, perPage));  // The offers to be displayed
        return modelAndView;
    }
}
