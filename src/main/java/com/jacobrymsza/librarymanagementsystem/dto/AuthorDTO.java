package com.jacobrymsza.librarymanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;
import java.util.ArrayList;

/**
 * Data Transfer Object for transferring author data between layers.
 */
public class AuthorDTO {
  private Long id;

  @NotBlank(message = "First name cannot be blank")
  private String firstName;

  @NotBlank(message = "Last name cannot be blank")
  private String lastName;

  public AuthorDTO() {
  }

  /**
   * Constructs a new AuthorDTO with the specified details.
   *
   * @param id the unique identifier of the author
   * @param firstName the first name of the author
   * @param lastName the last name of the author
   */
  public AuthorDTO(Long id, String firstName, String lastName) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }
}
