package com.crm.evaluation.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ClientDTO {
    
    @JsonProperty("id")
    private Long id;

    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty("address")
    private String address;

    @JsonProperty("zipcode")
    private String zipcode;

    @JsonProperty("city")
    private String city;

    @JsonProperty("company_name")
    private String companyName;

    @JsonProperty("vat")
    private String vat;

    @JsonProperty("company_type")
    private String companyType;

    @JsonProperty("client_number")
    private String clientNumber;

    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("industry_id")
    private Integer industryId;

    @JsonProperty("deleted_at")
    private LocalDateTime deletedAt;

    @JsonProperty("created_at")
    private LocalDateTime createdAt;

    @JsonProperty("updated_at")
    private LocalDateTime updatedAt;
}
