package com.crm.evaluation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.crm.evaluation.responses.OfferResponse;

@Service
public class OfferService {

    @Value("${api.base.url}")
    private String apiBaseUrl;

    @Autowired
    private RestTemplate restTemplate;

    public OfferResponse getOffers(int page, int perPage) {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl + "/offers")
                .queryParam("per_page", perPage)
                .queryParam("page", page)
                .toUriString();

        return restTemplate.getForObject(url, OfferResponse.class);
    }
}
