package com.martin.api.controller;

import com.martin.api.persistence.model.User;
import com.martin.api.service.ITaskService;
import com.martin.api.util.dto.AppResponse;
import com.martin.api.util.dto.DeleteResponse;
import com.martin.api.util.dto.TaskRequest;
import com.martin.api.util.dto.TaskResponse;
import com.martin.api.util.dto.UpdateTaskRequest;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/todos")
@RequiredArgsConstructor
@PreAuthorize("isAuthenticated()")
public class TaskController {

  private final ITaskService taskService;

  @GetMapping
  public ResponseEntity<AppResponse> allTodos(
      @PageableDefault(sort = {"title"}) Pageable pagination) {
    return ResponseEntity.ok(taskService.getAll(pagination));
  }

  @GetMapping("/{id}")
  public ResponseEntity<TaskResponse> getTodo(@PathVariable Long id) {
    return ResponseEntity.ok(taskService.getById(id));
  }

  @GetMapping("/me")
  public ResponseEntity<List<TaskResponse>> getTodosByUser(
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = extractUserId(userDetails);
    return ResponseEntity.ok(taskService.getByUser(userId));
  }

  @PostMapping
  public ResponseEntity<TaskResponse> createTodo(
      @AuthenticationPrincipal UserDetails userDetails,
      @RequestBody @Valid TaskRequest request) {
    Long userId = extractUserId(userDetails);
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(taskService.create(request, userId));
  }

  @PutMapping("/{id}")
  public ResponseEntity<TaskResponse> updateTodo(
      @PathVariable Long id,
      @RequestBody @Valid UpdateTaskRequest request,
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = extractUserId(userDetails);
    return ResponseEntity.status(HttpStatus.ACCEPTED)
        .body(taskService.update(id, userId, request));
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<DeleteResponse> deleteTodo(
      @PathVariable Long id,
      @AuthenticationPrincipal UserDetails userDetails) {
    Long userId = extractUserId(userDetails);
    return ResponseEntity.status(HttpStatus.ACCEPTED)
        .body(taskService.delete(id, userId));
  }

  private Long extractUserId(UserDetails userDetails) {
    return ((User) userDetails).getId();
  }
}
