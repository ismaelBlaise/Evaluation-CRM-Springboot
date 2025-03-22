package com.crm.evaluation.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;

@Service
public class LoginService {

    private final String LOGIN_API_URL = "http://localhost:8000/api/login";  

    @SuppressWarnings("deprecation")
    public String login(String email, String password) throws Exception {

        RestTemplate restTemplate = new RestTemplate();
        
        String url = LOGIN_API_URL + "?email=" + email + "&password=" + password;
        
        try {
            ResponseEntity<String> response = restTemplate.postForEntity(url, null, String.class);
            if (response.getStatusCodeValue() != 200) {
                throw new Exception("Identifiants incorrects.");
            }
            return response.getBody();
        } catch (Exception e) {
            throw new Exception("Erreur lors de la tentative de connexion.");
        }
    }
}
