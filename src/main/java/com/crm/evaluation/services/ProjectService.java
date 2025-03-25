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
import com.crm.evaluation.responses.ProjectResponse;

@Service
public class ProjectService {
    @Value("${api.base.url}")
    private String apiBaseUrl;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpSession httpSession;

    public ProjectResponse getProjects(int page, int perPage) {
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl + "/projects")
                .queryParam("per_page", perPage)
                .queryParam("page", page)
                .toUriString();

        HttpHeaders headers = createHeadersWithToken();
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(url, HttpMethod.GET, entity, ProjectResponse.class).getBody();
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
