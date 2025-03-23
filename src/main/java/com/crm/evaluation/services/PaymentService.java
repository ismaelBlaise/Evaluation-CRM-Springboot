package com.crm.evaluation.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.crm.evaluation.dtos.PaymentResponseDTO;

@Service
public class PaymentService {

    @Value("${api.base.url}")
    private String apiBaseUrl; 


    private final RestTemplate restTemplate;
   

    public PaymentService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public PaymentResponseDTO getPayments(int page,int perPage) {
        @SuppressWarnings("deprecation")
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl+"/payments")
                .queryParam("per_page", perPage)
                .queryParam("page",page)
                .toUriString();

        return restTemplate.getForObject(url, PaymentResponseDTO.class);
    }
}
