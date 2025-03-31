package com.jacobrymsza.librarymanagementsystem.repository;

import com.jacobrymsza.librarymanagementsystem.entity.Borrowing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Borrowing} entities.
 * Provides CRUD operations and custom queries for borrowings.
 */
@Repository
public interface BorrowingRepository extends JpaRepository<Borrowing, Long> {
  public Borrowing findByUserId(Long userId);
}
