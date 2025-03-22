package com.crm.evaluation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.crm.evaluation.services.LoginService;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    

    @PostMapping
    public ModelAndView login(@RequestParam String email,@RequestParam String password){
        ModelAndView modelAndView=new ModelAndView("template");

        try {
            loginService.login(email, password);
            modelAndView.addObject("page","dashboard" );
        } catch (Exception e) {
            modelAndView.setViewName("login");
            modelAndView.addObject("erreur", e.getMessage());
        }
        return modelAndView;
    }
}
