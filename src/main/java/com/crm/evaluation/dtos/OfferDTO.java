package com.crm.evaluation.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class OfferDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty("sent_at")
    private LocalDateTime sentAt;

    @JsonProperty("source_type")
    private String sourceType;

    @JsonProperty("source_id")
    private Long sourceId;

    @JsonProperty("client_id")
    private Long clientId;

    @JsonProperty("status")
    private String status;

    @JsonProperty("deleted_at")
    private LocalDateTime deletedAt;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
