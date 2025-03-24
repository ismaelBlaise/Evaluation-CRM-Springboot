package com.crm.evaluation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.crm.evaluation.responses.ClientResponse;
import com.crm.evaluation.responses.PaymentResponse;

@Service
public class ClientService {
    @Value("${api.base.url}")
    private String apiBaseUrl; 

    @Autowired
    private RestTemplate restTemplate;
   

   

    public ClientResponse getClients(int page,int perPage) {
        @SuppressWarnings("deprecation")
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl+"/clients")
                .queryParam("per_page", perPage)
                .queryParam("page",page)
                .toUriString();

        return restTemplate.getForObject(url, ClientResponse.class);
    }

}
