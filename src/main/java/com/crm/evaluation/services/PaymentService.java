package com.crm.evaluation.services;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.crm.evaluation.responses.PaymentResponse;

@Service
public class PaymentService {

    @Value("${api.base.url}")
    private String apiBaseUrl; 


    private final RestTemplate restTemplate;
   

    public PaymentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PaymentResponse getPayments(int page,int perPage) {
        @SuppressWarnings("deprecation")
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl+"/payments")
                .queryParam("per_page", perPage)
                .queryParam("page",page)
                .toUriString();

        return restTemplate.getForObject(url, PaymentResponse.class);
    }


    // public String updatePayment(Long paymentId, double newAmount) {
    //     @SuppressWarnings("deprecation")
    //     String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl + "/payments/update-2/" + paymentId)
    //             .toUriString();

    //     Map<String, Object> requestBody = new HashMap<>();
    //     requestBody.put("amount", newAmount);

    //     HttpHeaders headers = new HttpHeaders();
    //     headers.setContentType(MediaType.APPLICATION_JSON);

    //     HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

    //     try {
    //         ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
    //         return response.getBody();
    //     } catch (HttpClientErrorException.NotFound e) {
    //         return "Erreur : Paiement non trouvé (404)";
    //     } catch (HttpClientErrorException.BadRequest e) {
    //         return "Erreur : Requête invalide (400)";
    //     } catch (HttpServerErrorException e) {
    //         return "Erreur serveur : " + e.getMessage();
    //     } catch (ResourceAccessException e) {
    //         return "Erreur : Impossible de contacter le serveur Laravel";
    //     } catch (Exception e) {
    //         return "Erreur inconnue : " + e.getMessage();
    //     }
    // }


    

    public Map<String,String> updatePayment(Long paymentId, double newAmount) {
        @SuppressWarnings("deprecation")
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl + "/payments/update-2/" + paymentId)
                .toUriString();
    
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("amount", newAmount);
    
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
    
        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
    
        try {
            ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);
            Map<String, String> response = (Map<String, String>) responseEntity.getBody();
            
           return response;
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (HttpServerErrorException e) {
            throw e;
        } catch (ResourceAccessException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }
    


    private String extractErrorMessage(String json) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);
            if (rootNode.has("error")) {
                return "Erreur : " + rootNode.get("error").asText();
            }
        } catch (Exception e) {
            return "Erreur inattendue lors de la lecture de la réponse : " + e.getMessage();
        }
        return "Erreur inconnue du serveur";
    }


    public String deletePayment(Long paymentId) {
        @SuppressWarnings("deprecation")
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl + "/payments/delete/" + paymentId)
                .toUriString(); 

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", paymentId);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
            return response.getBody();
        } catch (HttpClientErrorException.NotFound e) {
            return "Erreur : Paiement non trouvé (404)";
        } catch (HttpClientErrorException.BadRequest e) {
            return "Erreur : Requête invalide (400)";
        } catch (HttpServerErrorException e) {
            return "Erreur serveur : " + e.getMessage();
        } catch (ResourceAccessException e) {
            return "Erreur : Impossible de contacter le serveur Laravel";
        } catch (Exception e) {
            return "Erreur inconnue : " + e.getMessage();
        }
    }

}
