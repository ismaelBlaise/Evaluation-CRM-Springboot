package com.crm.evaluation.responses;

import java.util.List;

import com.crm.evaluation.dtos.TaskDTO;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class TaskResponse {
    
    @JsonProperty("current_page")
    private int currentPage;

    @JsonProperty("data")
    private List<TaskDTO> data;

    @JsonProperty("first_page_url")
    private String firstPageUrl;

    @JsonProperty("from")
    private int from;

    @JsonProperty("last_page")
    private int lastPage;

    @JsonProperty("last_page_url")
    private String lastPageUrl;

    @JsonProperty("next_page_url")
    private String nextPageUrl;

    @JsonProperty("path")
    private String path;

    @JsonProperty("per_page")
    private int perPage;

    @JsonProperty("prev_page_url")
    private String prevPageUrl;

    @JsonProperty("to")
    private int to;

    @JsonProperty("total")
    private int total;
}