package com.crm.evaluation.services;

import java.util.List;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.crm.evaluation.dtos.StatutDTO;

@Service
public class StatutService {
    @Value("${api.base.url}")
    private String apiBaseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpSession httpSession;

    public List<StatutDTO> getStatutsProject() {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl + "/status/projects").toUriString();
        HttpHeaders headers = createHeadersWithToken();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<StatutDTO>>() {}).getBody();
    }

    public List<StatutDTO> getStatutsTasks() {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl + "/status/tasks").toUriString();
        HttpHeaders headers = createHeadersWithToken();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        return restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<List<StatutDTO>>() {}).getBody();
    }

    private HttpHeaders createHeadersWithToken() {
        String token = (String) httpSession.getAttribute("token");
        HttpHeaders headers = new HttpHeaders();
        if (token != null) {
            headers.set("Authorization", "Bearer " + token);
        }
        return headers;
    }
}
