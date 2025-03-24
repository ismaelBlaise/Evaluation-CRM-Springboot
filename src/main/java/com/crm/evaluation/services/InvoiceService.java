package com.crm.evaluation.services;

import com.crm.evaluation.responses.InvoiceResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class InvoiceService {

    @Value("${api.base.url}")
    private String apiBaseUrl;

    @Autowired
    private RestTemplate restTemplate;

    public InvoiceResponse getInvoices(int page, int perPage) {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl + "/invoices")
                .queryParam("per_page", perPage)
                .queryParam("page", page)
                .toUriString();

        return restTemplate.getForObject(url, InvoiceResponse.class);
    }
}
