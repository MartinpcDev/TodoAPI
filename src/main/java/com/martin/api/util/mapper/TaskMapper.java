package com.martin.api.util.mapper;

import com.martin.api.persistence.model.Task;
import com.martin.api.util.dto.TaskRequest;
import com.martin.api.util.dto.TaskResponse;
import com.martin.api.util.dto.UpdateTaskRequest;
import java.util.List;
import java.util.Objects;

public class TaskMapper {

  public static TaskResponse toTaskDTO(Task task) {
    if (task == null) {
      return null;
    }

    return TaskResponse.builder()
        .id(task.getId())
        .title(task.getTitle())
        .description(task.getDescription())
        .build();
  }

  public static List<TaskResponse> toTaskListDTO(List<Task> tasks) {
    if (tasks == null) {
      return null;
    }

    return tasks.stream()
        .map(TaskMapper::toTaskDTO)
        .toList();
  }

  public static Task toTaskEntity(TaskRequest request) {
    if (request == null) {
      return null;
    }
    Task task = new Task();
    task.setTitle(request.title());
    task.setDescription(request.description());
    return task;
  }

  public static void updateTaskEntity(Task oldTask, UpdateTaskRequest request) {
    if (oldTask != null && request != null) {
      if (!Objects.equals(request.title(), "")) {
        oldTask.setTitle(request.title());
      }
      if (!Objects.equals(request.description(), "")) {
        oldTask.setDescription(request.description());
      }
    }
  }
}
