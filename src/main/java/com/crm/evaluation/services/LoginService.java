package com.crm.evaluation.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

@Service
public class LoginService {

    private final String LOGIN_API_URL = "http://localhost:8000/api/login";   

    @SuppressWarnings("deprecation")
    public String login(String email, String password) throws Exception {

        Map<String, String> requestBody = new HashMap<>();
        requestBody.put("email", email);
        requestBody.put("password", password);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, String>> entity = new HttpEntity<>(requestBody, headers);

        RestTemplate restTemplate = new RestTemplate();

        try {
            ResponseEntity<String> response = restTemplate.exchange(LOGIN_API_URL, HttpMethod.POST, entity, String.class);

            if (response.getStatusCodeValue() != 200) {
                throw new Exception("Identifiants incorrects.");
            }

            return response.getBody();  
        } catch (HttpClientErrorException e) {
             
            throw new Exception("Erreur lors de la tentative de connexion: " + e.getResponseBodyAsString());
        } catch (Exception e) {
             
            throw new Exception("Erreur lors de la tentative de connexion.");
        }
    }
}
