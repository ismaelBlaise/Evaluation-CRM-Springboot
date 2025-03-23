package com.crm.evaluation.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.crm.evaluation.responses.DashboardResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import java.util.Map;

@Service
public class DashboardService {

    @Value("${api.base.url}")
    private String apiBaseUrl;

    public DashboardResponse getDashboardData() throws Exception {

        
        
        
        int nbClients = getEntityCount("clients/nb");
        int nbProjects = getEntityCount("projects/nb");
        int nbTasks = getEntityCount("tasks/nb");
        int nbOffers = getEntityCount("offers/nb");
        int nbInvoices = getEntityCount("invoices/nb");
        int nbPayments = getEntityCount("payments/nb");
        int nbInvoiceLines = getEntityCount("invoice-lines/nb");

        
        DashboardResponse dashboardResponse = new DashboardResponse(
                nbClients, nbProjects, nbTasks, nbOffers, nbInvoices, nbPayments, nbInvoiceLines
        );
        
        return dashboardResponse;
    }

     
    @SuppressWarnings("deprecation")
    private int getEntityCount(String endpoint) throws Exception {
        String url = apiBaseUrl + "/" + endpoint;

        RestTemplate restTemplate = new RestTemplate();
        
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        
        HttpEntity<String> entity = new HttpEntity<>(headers);
        
        try {
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            
            if (response.getStatusCodeValue() != 200) {
                throw new Exception("Erreur lors de la récupération des données.");
            }
            
            ObjectMapper objectMapper = new ObjectMapper();
            @SuppressWarnings("unchecked")
            Map<String, Integer> responseMap = objectMapper.readValue(response.getBody(), Map.class);
            
            
            return responseMap.values().stream().findFirst().orElse(0);
        } catch (HttpClientErrorException e) {
            throw new Exception("Erreur lors de l'appel API pour " + endpoint);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
