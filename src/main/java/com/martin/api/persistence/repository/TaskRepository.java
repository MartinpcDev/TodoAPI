package com.martin.api.persistence.repository;

import com.martin.api.persistence.model.Task;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskRepository extends JpaRepository<Task, Long> {

  List<Task> findAllByUserId(Long userId);
}
