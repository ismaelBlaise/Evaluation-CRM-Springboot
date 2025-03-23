package com.crm.evaluation.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class User {

    @JsonProperty("external_id")
    private String externalId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("email")
    private String email;

    @JsonProperty("address")
    private String address;

    @JsonProperty("primary_number")
    private String primaryNumber;

    @JsonProperty("secondary_number")
    private String secondaryNumber;

    @JsonProperty("language")
    private String language;

    @JsonProperty("deleted_at")
    private String deletedAt;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("updated_at")
    private String updatedAt;

    @JsonProperty("avatar")
    private String avatar;
}
