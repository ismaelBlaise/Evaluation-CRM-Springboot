package com.crm.evaluation.services;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.crm.evaluation.responses.OfferResponse;

@Service
public class OfferService {

    @Value("${api.base.url}")
    private String apiBaseUrl;

    private final RestTemplate restTemplate;
    private final HttpSession httpSession;

    public OfferService(RestTemplate restTemplate, HttpSession httpSession) {
        this.restTemplate = restTemplate;
        this.httpSession = httpSession;
    }

    public OfferResponse getOffers(int page, int perPage) {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl + "/offers")
                .queryParam("per_page", perPage)
                .queryParam("page", page)
                .toUriString();

        HttpHeaders headers = createHeadersWithToken();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET, entity, OfferResponse.class).getBody();
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
