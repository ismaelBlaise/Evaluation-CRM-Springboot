package com.crm.evaluation.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.crm.evaluation.responses.LoginResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

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
    public LoginResponse login(String email, String password) throws Exception {

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

            ObjectMapper objectMapper = new ObjectMapper();
            LoginResponse loginResponse = objectMapper.readValue(response.getBody(), LoginResponse.class);

            return loginResponse;
        } catch (HttpClientErrorException e) {
             
            throw new Exception(e.getMessage());
        } catch (Exception e) {
            
            throw new Exception(e.getMessage());
        }
    }
}
