package com.crm.evaluation.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.crm.evaluation.responses.ProjectResponse;
import com.crm.evaluation.responses.TaskResponse;

@Service
public class TaskService {
    @Value("${api.base.url}")
    private String apiBaseUrl; 

    @Autowired
    private RestTemplate restTemplate;
   
    
   

    public TaskResponse getTasks(int page,int perPage) {
        @SuppressWarnings("deprecation")
        String url = UriComponentsBuilder.fromHttpUrl(apiBaseUrl+"/tasks")
                .queryParam("per_page", perPage)
                .queryParam("page",page)
                .toUriString();

        return restTemplate.getForObject(url, TaskResponse.class);
    }
}
