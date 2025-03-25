package com.crm.evaluation.services;

import com.crm.evaluation.responses.InvoiceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class InvoiceService {

    @Value("${api.base.url}")
    private String apiBaseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpServletRequest request;  // Injecting the HttpServletRequest to access the session

    public InvoiceResponse getInvoices(int page, int perPage) {
        // Récupérer le token depuis la session
        HttpSession session = request.getSession();
        String token = (String) session.getAttribute("token");  // Le nom du token dans la session

        if (token == null) {
            throw new RuntimeException("Token d'authentification non trouvé dans la session.");
        }

        // Construire l'URL avec les paramètres
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl + "/invoices")
                .queryParam("per_page", perPage)
                .queryParam("page", page)
                .toUriString();

        // Créer l'en-tête avec le token
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);

        // Créer une entité avec l'en-tête
        HttpEntity<String> entity = new HttpEntity<>(headers);

        // Effectuer la requête avec l'en-tête
        return restTemplate.exchange(url, HttpMethod.GET, entity, InvoiceResponse.class).getBody();
    }
}
