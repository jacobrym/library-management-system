package com.jacobrymsza.librarymanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

/**
 * A Data Transfer Object (DTO) representing a book in the library system.
 * This class is used to transfer book data between layers of the application,
 * avoiding cyclic reference issues during JSON serialization. It includes the book's
 * identifier, title, ISBN, and a list of author names.
 */
public class BookDTO {
  private Long id;

  @NotBlank(message = "Title cannot be blank")
  private String title;

  @NotBlank(message = "ISBN cannot be blank")
  private String isbn;

  @NotEmpty(message = "At least one author must be selected")
  private List<String> authorNames;

  public BookDTO() {
    this.authorNames = new ArrayList<>();
  }

  /**
   * Constructs a new {@link BookDTO} instance with the specified book details.
   *
   * @param id the unique identifier of the book
   * @param title the title of the book
   * @param isbn the International Standard Book Number (ISBN) of the book
   * @param authorNames a {@link List} of strings representing the names of the book's authors
   */
  public BookDTO(Long id, String title, String isbn, List<String> authorNames) {
    this.id = id;
    this.title = title;
    this.isbn = isbn;
    this.authorNames = authorNames;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getIsbn() {
    return isbn;
  }

  public void setIsbn(String isbn) {
    this.isbn = isbn;
  }

  public List<String> getAuthorNames() {
    return authorNames;
  }

  public void setAuthorNames(List<String> authorNames) {
    this.authorNames = authorNames;
  }
}
