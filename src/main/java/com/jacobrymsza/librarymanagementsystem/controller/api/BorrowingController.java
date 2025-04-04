package com.jacobrymsza.librarymanagementsystem.controller.api;

import com.jacobrymsza.librarymanagementsystem.dto.BorrowRequestDTO;
import com.jacobrymsza.librarymanagementsystem.dto.BorrowingDTO;
import com.jacobrymsza.librarymanagementsystem.service.BorrowingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing borrowing-related operations in the library system.
 * Provides endpoints for borrowing and returning books, and retrieving borrowing details.
 */
@RestController
@RequestMapping("/api/borrowings")
public class BorrowingController {
  private final BorrowingService borrowingService;

  @Autowired
  public BorrowingController(BorrowingService borrowingService) {
    this.borrowingService = borrowingService;
  }

  /**
   * Borrows a book for a user based on the provided request.
   *
   * @param request the borrowing request containing book ID and user ID
   * @return ResponseEntity containing the created BorrowingDTO with HTTP status 201
   */
  @PostMapping
  public ResponseEntity<?> borrowBook(@RequestBody BorrowRequestDTO request) {
    try {
      if (request == null || request.getBookId() == null || request.getUserId() == null) {
        throw new IllegalArgumentException("Book ID and User ID are required");
      }
      BorrowingDTO borrowing = borrowingService.borrowBook(request.getBookId(),
                                                           request.getUserId());
      return ResponseEntity.status(201).body(borrowing);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  /**
   * Handles the HTTP PUT request to return a borrowed book.
   *
   * @param id the ID of the borrowing record to be returned
   * @return ResponseEntity containing the updated BorrowingDTO if successful,
   *         or a bad request response with an error message if the return operation fails.
   */
  @PutMapping("/{id}/return")
  public ResponseEntity<?> returnBook(@PathVariable Long id) {
    try {
      BorrowingDTO borrowing = borrowingService.returnBook(id);
      return ResponseEntity.ok(borrowing);
    } catch (IllegalArgumentException e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

  @GetMapping("/{id}")
  public ResponseEntity<BorrowingDTO> getBorrowingById(@PathVariable Long id) {
    BorrowingDTO borrowing = borrowingService.getBorrowingById(id);
    return ResponseEntity.ok(borrowing);
  }
}

