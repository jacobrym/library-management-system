package com.jacobrymsza.librarymanagementsystem.repository;

import com.jacobrymsza.librarymanagementsystem.entity.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Author} entities.
 * Provides basic CRUD operations and custom queries for authors.
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
  public Author findByLastName(String lastName);
}
