package com.crm.evaluation.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class InvoiceDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty("sent_at")
    private String sentAt;

    @JsonProperty("due_at")
    private String dueAt;

    @JsonProperty("status")
    private String status;

    @JsonProperty("invoice_number")
    private Long invoiceNumber;

    @JsonProperty("client_id")
    private Long clientId;

    @JsonProperty("source_type")
    private String sourceType;

    @JsonProperty("source_id")
    private Long sourceId;

    @JsonProperty("offer_id")
    private Long offerId;

    @JsonProperty("deleted_at")
    private LocalDateTime deletedAt;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
