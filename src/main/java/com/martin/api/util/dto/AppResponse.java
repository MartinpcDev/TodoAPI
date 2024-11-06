package com.martin.api.util.dto;

import java.util.List;

public record AppResponse(
    List<TaskResponse> data,
    Integer page,
    Integer limit,
    Integer total
) {

}
