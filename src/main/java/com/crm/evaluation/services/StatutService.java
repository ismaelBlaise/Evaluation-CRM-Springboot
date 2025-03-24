package com.crm.evaluation.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
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

    public List<StatutDTO> getStatutsProject() {
        // Construire l'URL sans paramètres de pagination
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl + "/status/projects")
                .toUriString();

        // Récupérer la liste des statuts sans pagination
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<StatutDTO>>() {}).getBody();
    }


    public List<StatutDTO> getStatutsTasks() {
        // Construire l'URL sans paramètres de pagination
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl + "/status/tasks")
                .toUriString();

        // Récupérer la liste des statuts sans pagination
        return restTemplate.exchange(url, HttpMethod.GET, null, new ParameterizedTypeReference<List<StatutDTO>>() {}).getBody();
    }
}
