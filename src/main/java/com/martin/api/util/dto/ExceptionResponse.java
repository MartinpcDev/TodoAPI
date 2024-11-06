package com.martin.api.util.dto;

import java.util.List;

public record ExceptionResponse(
    String message,
    String exceptionMessage,
    List<String> details
) {

}
