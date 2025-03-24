package com.crm.evaluation.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class StatutDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("source_type")
    private String sourceType;

    @JsonProperty("color")
    private String color;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;

  
}
