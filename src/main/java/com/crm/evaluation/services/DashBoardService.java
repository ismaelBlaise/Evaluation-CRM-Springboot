package com.crm.evaluation.services;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.crm.evaluation.dtos.MapDTO;
import com.crm.evaluation.responses.DashboardResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.MediaType;
import org.springframework.web.client.HttpClientErrorException;
import jakarta.servlet.http.HttpSession;
import java.util.Map;

@Service
public class DashboardService {

    @Value("${api.base.url}")
    private String apiBaseUrl;

    private final HttpSession httpSession;

    public DashboardService(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    public DashboardResponse getDashboardData() throws Exception {
        Map<String, Integer> projectStatusCount = getProjectCountByStatus().getDataInteger();
        int nbProjects = projectStatusCount.getOrDefault("Open", 0);  

        int nbClients = getEntityCount("clients/nb");
        int nbTasks = getEntityCount("tasks/nb");
        int nbOffers = getEntityCount("offers/nb");
        int nbInvoices = getEntityCount("invoices/nb");
        int nbPayments = getEntityCount("payments/nb");
        double sumPayments = getEntitySum("payments/sum");
        int nbInvoiceLines = getEntityCount("invoice-lines/nb");

        DashboardResponse dashboardResponse = new DashboardResponse(
                nbClients, nbProjects, nbTasks, nbOffers, nbInvoices, nbPayments, nbInvoiceLines
        );
        dashboardResponse.setSumPayments(sumPayments);
        
        return dashboardResponse;
    }

    private int getEntityCount(String endpoint) throws Exception {
        String url = apiBaseUrl + "/" + endpoint;
        HttpHeaders headers = createHeadersWithToken();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return fetchIntegerData(url, entity);
    }

    private double getEntitySum(String endpoint) throws Exception {
        String url = apiBaseUrl + "/" + endpoint;
        HttpHeaders headers = createHeadersWithToken();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return fetchDoubleData(url, entity);
    }

    public MapDTO getProjectCountByStatus() throws Exception {
        String url = apiBaseUrl + "/projects/chart"; 
        HttpHeaders headers = createHeadersWithToken();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return fetchMapData(url, entity);
    }

    public MapDTO getInvoicePaymentSummary(String annee, String mois) throws Exception {
        String url = apiBaseUrl + "/invoices/chart"; 
        if (annee != null) url += "/" + annee;
        if (mois != null) url += "/" + mois;

        HttpHeaders headers = createHeadersWithToken();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return fetchDoubleMapData(url, entity);
    }

    public MapDTO getMonthlyRevenueChart() throws Exception {
        String url = apiBaseUrl + "/payments/chart"; 
        HttpHeaders headers = createHeadersWithToken();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return fetchMapData(url, entity);
    }

    private HttpHeaders createHeadersWithToken() {
        String token = (String) httpSession.getAttribute("token");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        if (token != null) {
            headers.set("Authorization", "Bearer " + token);
        }
        return headers;
    }

    private int fetchIntegerData(String url, HttpEntity<String> entity) throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Integer> responseMap = objectMapper.readValue(response.getBody(), Map.class);
            return responseMap.values().stream().findFirst().orElse(0);
        } catch (HttpClientErrorException e) {
            throw new Exception("Erreur API pour " + url);
        }
    }

    private double fetchDoubleData(String url, HttpEntity<String> entity) throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Number> responseMap = objectMapper.readValue(response.getBody(), Map.class);
            return responseMap.values().stream().findFirst().map(Number::doubleValue).orElse(0.0);
        } catch (HttpClientErrorException e) {
            throw new Exception("Erreur API pour " + url);
        }
    }

    private MapDTO fetchMapData(String url, HttpEntity<String> entity) throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Integer> data = objectMapper.readValue(response.getBody(), Map.class);
            MapDTO mapDTO = new MapDTO();
            mapDTO.setDataInteger(data);
            return mapDTO;
        } catch (HttpClientErrorException e) {
            throw new Exception("Erreur API pour " + url);
        }
    }

    private MapDTO fetchDoubleMapData(String url, HttpEntity<String> entity) throws Exception {
        try {
            RestTemplate restTemplate = new RestTemplate();
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
            ObjectMapper objectMapper = new ObjectMapper();
            Map<String, Double> data = objectMapper.readValue(response.getBody(), new TypeReference<Map<String, Double>>() {});
            MapDTO mapDTO = new MapDTO();
            mapDTO.setDataDouble(data);
            return mapDTO;
        } catch (HttpClientErrorException e) {
            throw new Exception("Erreur API pour " + url);
        }
    }
}
