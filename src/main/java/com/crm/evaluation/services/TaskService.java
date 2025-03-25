package com.crm.evaluation.services;

import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import com.crm.evaluation.responses.TaskResponse;

@Service
public class TaskService {
    @Value("${api.base.url}")
    private String apiBaseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpSession httpSession;

    public TaskResponse getTasks(int page, int perPage) {
        @SuppressWarnings("deprecation")
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl + "/tasks")
                .queryParam("per_page", perPage)
                .queryParam("page", page)
                .toUriString();

        HttpHeaders headers = createHeadersWithToken();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET, entity, TaskResponse.class).getBody();
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
