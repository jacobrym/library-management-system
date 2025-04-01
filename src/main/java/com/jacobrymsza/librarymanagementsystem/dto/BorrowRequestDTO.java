package com.jacobrymsza.librarymanagementsystem.dto;

/**
 * Data Transfer Object for transferring borrowing request data.
 */
public class BorrowRequestDTO {
  private Long bookId;
  private Long userId;

  public Long getBookId() {
    return bookId;
  }

  public void setBookId(Long bookId) {
    this.bookId = bookId;
  }

  public Long getUserId() {
    return userId;
  }

  public void setUserId(Long userId) {
    this.userId = userId;
  }
}