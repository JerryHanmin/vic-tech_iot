package com.vic.iot.common.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
public class ErrorReponse {
    public ErrorReponse() {
    }

    public ErrorReponse(String error, String errorDescription) {
        this.error = error;
        this.errorDescription = errorDescription;
    }


    private String error;
    @JsonProperty("error_description")
    private String errorDescription;
    private List<ErrorMessage> messages;
    @JsonProperty("additional_info")
    private Map<String, Object> additionalInfo;
}
