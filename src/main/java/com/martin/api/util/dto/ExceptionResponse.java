package com.martin.api.util.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

public record ExceptionResponse(
    String message,
    @JsonProperty("server_message")
    String exceptionMessage,
    List<String> details
) {

}
