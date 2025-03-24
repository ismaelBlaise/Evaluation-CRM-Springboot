package com.crm.evaluation.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @GetMapping("/update-form/{id}/{amount}")
    public ModelAndView updateForm(@PathVariable Long id,@PathVariable double amount) {
        ModelAndView modelAndView = new ModelAndView("template");
        modelAndView.addObject("page","payments/update");
        modelAndView.addObject("paymentId", id);
        modelAndView.addObject("amount", amount/100);
        return modelAndView;
    }


    @PostMapping("/update")
    public ModelAndView updatePayment(@RequestParam Long id, @RequestParam double amount) {
        ModelAndView modelAndView = new ModelAndView("template");
        modelAndView.addObject("page","payments/update");
        modelAndView.addObject("paymentId", id);
        modelAndView.addObject("amount", amount);
    
        try {
            Map<String,String> update=paymentService.updatePayment(id, amount);
            if(!update.containsKey("error")){
                modelAndView.addObject("succes", "Montant modifier avec succes");
            }
            else {
                modelAndView.addObject("erreur", update.get("error"));
            }
        } catch (IllegalArgumentException e) {
            modelAndView.addObject("erreur", "Erreur: Montant invalide.");
        } catch (Exception e) {
            modelAndView.addObject("erreur", e.getMessage());
        }
    
        return modelAndView;
    }


    @GetMapping("/delete/{id}")
    public ModelAndView deletePayment( @RequestParam(value = "page", defaultValue = "1") int page,@RequestParam(value = "per_page", defaultValue = "10") int perPage,@PathVariable Long id) {
        ModelAndView modelAndView=new ModelAndView("template");
        modelAndView.addObject("page","payments/payments");
        modelAndView.addObject("payments",paymentService.getPayments(page, perPage));
    
        try {
            paymentService.deletePayment(id);
            modelAndView.addObject("succes", "Payment supprimer avec succes");
        
        } catch (Exception e) {
            modelAndView.addObject("error", "Une erreur est survenue lors de la mise à jour du paiement.");
        }
    
        return modelAndView;
    }
    
}
