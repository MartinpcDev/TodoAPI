package com.martin.api.persistence.repository;

import com.martin.api.persistence.model.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

  Optional<User> findByEmailIgnoreCase(String email);
}
