package com.crm.evaluation.services;

import jakarta.servlet.http.HttpSession;
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

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Value("${api.base.url}")
    private String apiBaseUrl;

    private final RestTemplate restTemplate;
    private final HttpSession httpSession;

    public PaymentService(RestTemplate restTemplate, HttpSession httpSession) {
        this.restTemplate = restTemplate;
        this.httpSession = httpSession;
    }

    public PaymentResponse getPayments(int page, int perPage) {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl + "/payments")
                .queryParam("per_page", perPage)
                .queryParam("page", page)
                .toUriString();

        HttpHeaders headers = createHeadersWithToken();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET, entity, PaymentResponse.class).getBody();
    }

    public Map<String, String> updatePayment(Long paymentId, double newAmount) {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl + "/payments/update-2/" + paymentId)
                .toUriString();

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("amount", newAmount);

        HttpHeaders headers = createHeadersWithToken();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);

        try {
            ResponseEntity<Map> responseEntity = restTemplate.exchange(url, HttpMethod.POST, requestEntity, Map.class);
            Map<String, String> response = (Map<String, String>) responseEntity.getBody();
            return response;
        } catch (HttpClientErrorException | HttpServerErrorException | ResourceAccessException e) {
            throw e;
        } catch (Exception e) {
            throw e;
        }
    }

    public String deletePayment(Long paymentId) {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl + "/payments/delete/" + paymentId)
                .toUriString();

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("id", paymentId);

        HttpHeaders headers = createHeadersWithToken();
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

    private HttpHeaders createHeadersWithToken() {
        String token = (String) httpSession.getAttribute("token");
        HttpHeaders headers = new HttpHeaders();
        if (token != null) {
            headers.set("Authorization", "Bearer " + token);
        }
        return headers;
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
}
