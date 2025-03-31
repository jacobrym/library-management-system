package com.jacobrymsza.librarymanagementsystem.repository;

import com.jacobrymsza.librarymanagementsystem.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Book} entities.
 * Provides CRUD operations and custom queries for books.
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
  public Book findByIsbn(String isbn);
}
