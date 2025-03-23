package com.crm.evaluation.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.crm.evaluation.responses.LoginResponse;
import com.crm.evaluation.services.LoginService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/login")
public class LoginController {
    @Autowired
    private LoginService loginService;
    

    @PostMapping
    public ModelAndView login(@RequestParam String email,@RequestParam String password,HttpSession session){
        ModelAndView modelAndView=new ModelAndView("redirect:/dashboard");

        try {
            LoginResponse loginResponse=loginService.login(email, password);
             
            session.setAttribute("token", loginResponse.getToken());

            session.setAttribute("user",loginResponse.getUser());
            
        } catch (Exception e) {
            e.printStackTrace();
            modelAndView.setViewName("login");
            modelAndView.addObject("erreur", e.getMessage());
        }
        return modelAndView;
    }
}
