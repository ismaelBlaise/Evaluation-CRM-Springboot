package com.crm.evaluation.services;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.crm.evaluation.responses.ClientResponse;

@Service
public class ClientService {
    
    @Value("${api.base.url}")
    private String apiBaseUrl; 

    @Autowired
    private RestTemplate restTemplate;
    
    @Autowired
    private HttpSession session;  

    public ClientResponse getClients(int page, int perPage) {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl + "/clients")
                .queryParam("per_page", perPage)
                .queryParam("page", page)
                .toUriString();

        
        String token = (String) session.getAttribute("token");
        if (token == null) {
            throw new RuntimeException("Token non trouvé en session !");
        }

        
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + token);
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        // Effectuer la requête avec les en-têtes
        ResponseEntity<ClientResponse> response = restTemplate.exchange(
                url, HttpMethod.GET, entity, ClientResponse.class);
        
        return response.getBody();
    }
}
