package com.crm.evaluation.responses;

import com.crm.evaluation.models.User;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginResponse {
    @JsonProperty("message")
    String message;
    @JsonProperty("user")
    User user;
    @JsonProperty("token")
    String token;

}
