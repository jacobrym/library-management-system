package com.jacobrymsza.librarymanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.ArrayList;

/**
 * Data Transfer Object for transferring borrowing data between layers.
 */
public class BorrowingDTO {
  private Long id;

  @NotBlank(message = "Title cannot be blank")
  private String bookTitle;

  @NotBlank(message = "Username cannot be blank")
  private String userUsername;

  private LocalDateTime borrowDate;
  private LocalDateTime returnDate;

  public BorrowingDTO() {
  }

  /**
   * Constructs a new BorrowingDTO with the specified details.
   *
   * @param id the unique identifier of the borrowing record
   * @param bookTitle the title of the borrowed book
   * @param userUsername the username of the user who borrowed the book
   * @param borrowDate the date and time the book was borrowed
   * @param returnDate the date and time the book was returned, or null if not returned
   */
  public BorrowingDTO(Long id, String bookTitle, String userUsername, LocalDateTime borrowDate,
                      LocalDateTime returnDate) {
    this.id = id;
    this.bookTitle = bookTitle;
    this.userUsername = userUsername;
    this.borrowDate = borrowDate;
    this.returnDate = returnDate;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getBookTitle() {
    return bookTitle;
  }

  public void setBookTitle(String bookTitle) {
    this.bookTitle = bookTitle;
  }

  public String getUserUsername() {
    return userUsername;
  }

  public void setUserUsername(String userUsername) {
    this.userUsername = userUsername;
  }

  public LocalDateTime getBorrowDate() {
    return borrowDate;
  }

  public void setBorrowDate(LocalDateTime borrowDate) {
    this.borrowDate = borrowDate;
  }

  public LocalDateTime getReturnDate() {
    return returnDate;
  }

  public void setReturnDate(LocalDateTime returnDate) {
    this.returnDate = returnDate;
  }
}
