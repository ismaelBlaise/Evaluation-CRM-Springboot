package com.crm.evaluation.controllers;

import com.crm.evaluation.responses.DashboardResponse;
import com.crm.evaluation.services.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpSession;
import reactor.core.publisher.Mono;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public ModelAndView getDashboardData(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("template");
        modelAndView.addObject("page", "dashboard");
        String token = (String) session.getAttribute("token");

        
        try {
            DashboardResponse dashboardResponse = dashboardService.getDashboardData();
           
            session.setAttribute("user",session.getAttribute("user"));
            modelAndView.addObject("nbClients", dashboardResponse.getNbClients());
            modelAndView.addObject("nbProjects", dashboardResponse.getNbProjects());
            modelAndView.addObject("nbTasks", dashboardResponse.getNbTasks());
            modelAndView.addObject("nbOffers", dashboardResponse.getNbOffers());
            modelAndView.addObject("nbInvoices", dashboardResponse.getNbInvoices());
            modelAndView.addObject("nbPayments", dashboardResponse.getNbPayments());
            System.out.println(dashboardResponse.getSumPayments());
            modelAndView.addObject("sumPayments", dashboardResponse.getSumPayments());
            modelAndView.addObject("nbInvoiceLines", dashboardResponse.getNbInvoiceLines());
        } catch (Exception e) {
            e.printStackTrace();

            modelAndView.addObject("erreur", "Erreur lors de la récupération des données du tableau de bord.");
        }
        return modelAndView;
    }
}
