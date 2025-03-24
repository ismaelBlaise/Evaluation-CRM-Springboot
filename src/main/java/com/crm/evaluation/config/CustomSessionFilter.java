package com.crm.evaluation.config;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Set;

@Component
public class CustomSessionFilter implements Filter {

    private static final Set<String> AUTHORIZED_URLS = Set.of("/", "/users/login");
    private static final String STATIC_RESOURCES = "/assets/";

    @Override
    public void doFilter(jakarta.servlet.ServletRequest request,
                         jakarta.servlet.ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String requestURI = httpRequest.getRequestURI();
        HttpSession session = httpRequest.getSession(false); // Récupérer la session sans la créer

        // 🔹 Autoriser les URL publiques et les ressources statiques
        if (requestURI.startsWith(STATIC_RESOURCES) || AUTHORIZED_URLS.contains(requestURI)) {
            chain.doFilter(request, response);
            return;
        }

        // 🔹 Vérifier si une session existe et si un utilisateur est connecté
        if (session == null || session.getAttribute("user") == null) {
            httpResponse.sendRedirect("/");
            return;
        }

        chain.doFilter(request, response);
    }
}
