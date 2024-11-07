package com.martin.api.service.impl;

import com.martin.api.exception.TaskNotFoundException;
import com.martin.api.exception.TaskPermissionsException;
import com.martin.api.exception.UserNotFoundException;
import com.martin.api.persistence.model.Task;
import com.martin.api.persistence.model.User;
import com.martin.api.persistence.repository.TaskRepository;
import com.martin.api.persistence.repository.UserRepository;
import com.martin.api.service.ITaskService;
import com.martin.api.util.dto.AppResponse;
import com.martin.api.util.dto.DeleteResponse;
import com.martin.api.util.dto.TaskRequest;
import com.martin.api.util.dto.TaskResponse;
import com.martin.api.util.dto.UpdateTaskRequest;
import com.martin.api.util.mapper.TaskMapper;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements ITaskService {

  private final TaskRepository taskRepository;
  private final UserRepository userRepository;

  @Override
  public AppResponse getAll(Pageable page) {

    Page<Task> taskPage = taskRepository.findAll(page);
    return new AppResponse(
        TaskMapper.toTaskListDTO(taskPage.getContent()),
        taskPage.getNumber() + 1,
        taskPage.getPageable().getPageSize(),
        (int) taskPage.getTotalElements());
  }

  @Override
  public List<TaskResponse> getByUser(Long userId) {
    return TaskMapper.toTaskListDTO(taskRepository.findAllByUserId(userId));
  }

  @Override
  public TaskResponse getById(Long taskId) {
    Task task = taskRepository.findById(taskId)
        .orElseThrow(() -> new TaskNotFoundException("No existe una task con el id " + taskId));
    return TaskMapper.toTaskDTO(task);
  }

  @Override
  @Transactional
  public TaskResponse create(TaskRequest request, Long userId) {
    User userExists = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("No este usuario"));
    Task task = TaskMapper.toTaskEntity(request);
    task.setUser(userExists);
    return TaskMapper.toTaskDTO(taskRepository.save(task));
  }

  @Override
  @Transactional
  public TaskResponse update(Long taskId, Long userId, UpdateTaskRequest request) {
    User userExists = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("No este usuario"));
    Task taskExist = taskRepository.findById(taskId)
        .orElseThrow(() -> new TaskNotFoundException("No existe una task con el id " + taskId));

    if (!Objects.equals(userExists.getId(), taskExist.getUser().getId())) {
      throw new TaskPermissionsException("Error..Esta Task pertenece a otro usuario");
    }

    TaskMapper.updateTaskEntity(taskExist, request);
    return TaskMapper.toTaskDTO(taskRepository.save(taskExist));
  }

  @Override
  @Transactional
  public DeleteResponse delete(Long taskId, Long userId) {
    User userExists = userRepository.findById(userId)
        .orElseThrow(() -> new UserNotFoundException("No este usuario"));
    Task taskExist = taskRepository.findById(taskId)
        .orElseThrow(() -> new TaskNotFoundException("No existe una task con el id " + taskId));

    if (!Objects.equals(userExists.getId(), taskExist.getUser().getId())) {
      throw new TaskPermissionsException("Error..Esta Task pertenece a otro usuario");
    }

    taskRepository.delete(taskExist);

    return new DeleteResponse("Task eliminado Correctamente");
  }
}
