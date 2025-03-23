package com.crm.evaluation.responses;

import com.crm.evaluation.dtos.UserDTO;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class LoginResponse {
    @JsonProperty("message")
    String message;
    @JsonProperty("user")
    UserDTO user;
    @JsonProperty("token")
    String token;

}
