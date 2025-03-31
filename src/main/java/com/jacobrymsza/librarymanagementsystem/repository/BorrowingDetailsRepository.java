package com.jacobrymsza.librarymanagementsystem.repository;

import com.jacobrymsza.librarymanagementsystem.entity.BorrowingDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link BorrowingDetails} entities.
 * Provides CRUD operations for borrowing details.
 */
@Repository
public interface BorrowingDetailsRepository extends JpaRepository<BorrowingDetails, Long> {
}
