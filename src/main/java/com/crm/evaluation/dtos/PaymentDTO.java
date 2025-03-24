package com.crm.evaluation.dtos;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class PaymentDTO {
    
    @JsonProperty("id")
    private int id;

    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty("amount")
    private double amount;

    @JsonProperty("description")
    private String description;

    @JsonProperty("payment_source")
    private String paymentSource;

    @JsonProperty("payment_date")
    private LocalDateTime paymentDate;

    @JsonProperty("integration_payment_id")
    private String integrationPaymentId;

    @JsonProperty("integration_type")
    private String integrationType;

    @JsonProperty("invoice_id")
    private int invoiceId;

    @JsonProperty("deleted_at")
    private LocalDateTime deletedAt;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
