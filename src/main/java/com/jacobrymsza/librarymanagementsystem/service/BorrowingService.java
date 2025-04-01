package com.jacobrymsza.librarymanagementsystem.service;

import com.jacobrymsza.librarymanagementsystem.dto.BorrowingDTO;
import com.jacobrymsza.librarymanagementsystem.entity.Book;
import com.jacobrymsza.librarymanagementsystem.entity.Borrowing;
import com.jacobrymsza.librarymanagementsystem.entity.User;
import com.jacobrymsza.librarymanagementsystem.repository.BookRepository;
import com.jacobrymsza.librarymanagementsystem.repository.BorrowingRepository;
import com.jacobrymsza.librarymanagementsystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing {@link Borrowing} entities.
 * Handles business logic related to borrowing books in the library system.
 */
@Service
public class BorrowingService {
  private final BorrowingRepository borrowingRepository;
  private final BookRepository bookRepository;
  private final UserRepository userRepository;

  /**
   * Constructs a new {@code BorrowingService} instance with the specified repositories.
   * This constructor uses Spring's dependency injection to provide access to the data
   * access objects required for managing borrowings, books, and users in the library
   * management system.
   *
   * @param borrowingRepository the repository for managing {@code Borrowing} entities,
   *                            must not be null
   * @param bookRepository the repository for managing {@code Book} entities, must not be null
   * @param userRepository the repository for managing {@code User} entities, must not be null
   */
  @Autowired
  public BorrowingService(BorrowingRepository borrowingRepository,
                          BookRepository bookRepository,
                          UserRepository userRepository) {
    this.borrowingRepository = borrowingRepository;
    this.bookRepository = bookRepository;
    this.userRepository = userRepository;
  }

  /**
   * Creates a new borrowing record for a specified book and user.
   *
   * @param bookId the ID of the book to be borrowed
   * @param userId the ID of the user borrowing the book
   * @return the created BorrowingDTO entity
   * @throws IllegalArgumentException if the book or user with the given ID does not exist
   */
  @Transactional
  public BorrowingDTO borrowBook(Long bookId, Long userId) {
    Book book = bookRepository.findById(bookId)
            .orElseThrow(() -> new IllegalArgumentException("Book with ID "
                                                            + bookId + " not found"));
    User user = userRepository.findById(userId)
            .orElseThrow(() -> new IllegalArgumentException("User with ID "
                                                            + userId + " not found"));

    Borrowing borrowing = new Borrowing();
    borrowing.setBook(book);
    borrowing.setUser(user);
    borrowing.setBorrowDate(LocalDateTime.now());

    Borrowing savedBorrowing = borrowingRepository.save(borrowing);
    return mapToDTO(savedBorrowing);
  }

  /**
   * Marks a borrowed book as returned by setting its return date.
   *
   * @param borrowingId the ID of the borrowing record to update
   * @return the updated BorrowingDTO
   * @throws IllegalArgumentException if the borrowing ID is invalid or the book is already returned
   */
  @Transactional
  public BorrowingDTO returnBook(Long borrowingId) {
    Borrowing borrowing = borrowingRepository.findById(borrowingId)
            .orElseThrow(() -> new IllegalArgumentException("Borrowing with ID "
                                                            + borrowingId + " not found"));
    if (borrowing.getReturnDate() != null) {
      throw new IllegalArgumentException("Book with borrowing ID "
                                         + borrowingId + " has already been returned");
    }

    borrowing.setReturnDate(LocalDateTime.now());
    Borrowing updatedBorrowing = borrowingRepository.save(borrowing);
    return mapToDTO(updatedBorrowing);
  }

  /**
   * Retrieves a borrowing record by its ID.
   *
   * @param id the ID of the borrowing record to retrieve
   * @return the BorrowingDTO containing borrowing details
   * @throws IllegalArgumentException if the borrowing with the specified ID is not found
   */
  public BorrowingDTO getBorrowingById(Long id) {
    Borrowing borrowing = borrowingRepository.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Borrowing with ID "
                                                            + id + " not found"));
    return mapToDTO(borrowing);
  }

  private BorrowingDTO mapToDTO(Borrowing borrowing) {
    return new BorrowingDTO(
            borrowing.getId(),
            borrowing.getBook().getTitle(),
            borrowing.getUser().getUsername(),
            borrowing.getBorrowDate(),
            borrowing.getReturnDate()
    );
  }
}
