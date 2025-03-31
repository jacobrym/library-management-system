package com.jacobrymsza.librarymanagementsystem.repository;

import com.jacobrymsza.librarymanagementsystem.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link User} entities.
 * Provides CRUD operations and custom queries for users.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
  public User findByUsername(String username);
}
