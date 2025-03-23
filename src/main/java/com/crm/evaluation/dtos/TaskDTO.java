package com.crm.evaluation.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("status_id")
    private Integer statusId;

    @JsonProperty("user_assigned_id")
    private Long userAssignedId;

    @JsonProperty("user_created_id")
    private Long userCreatedId;

    @JsonProperty("client_id")
    private Long clientId;

    @JsonProperty("project_id")
    private Long projectId;

    @JsonProperty("deadline")
    private LocalDateTime deadline;

    @JsonProperty("deleted_at")
    private LocalDateTime deletedAt;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
