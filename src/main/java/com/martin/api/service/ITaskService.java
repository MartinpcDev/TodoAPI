package com.martin.api.service;

import com.martin.api.util.dto.AppResponse;
import com.martin.api.util.dto.DeleteResponse;
import com.martin.api.util.dto.TaskRequest;
import com.martin.api.util.dto.TaskResponse;
import com.martin.api.util.dto.UpdateTaskRequest;
import java.util.List;
import org.springframework.data.domain.Pageable;

public interface ITaskService {

  AppResponse getAll(Pageable page);

  List<TaskResponse> getByUser(Long userId);

  TaskResponse getById(Long taskId);

  TaskResponse create(TaskRequest request, Long userId);

  TaskResponse update(Long taskId, Long userId, UpdateTaskRequest request);

  DeleteResponse delete(Long taskId, Long userId);
}
