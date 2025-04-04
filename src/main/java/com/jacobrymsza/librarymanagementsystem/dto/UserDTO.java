package com.jacobrymsza.librarymanagementsystem.dto;

import jakarta.validation.constraints.NotBlank;

/**
 * Data Transfer Object for representing a user in the library system.
 */
public class UserDTO {
  private Long id;

  @NotBlank(message = "Username cannot be blank")
  private String username;

  @NotBlank(message = "Email cannot be blank")
  private String email;

  /**
   * Default constructor for creating an empty UserDTO.
   */
  public UserDTO() {
  }

  /**
   * Constructs a new {@code UserDTO} instance with the specified ID, username, and email.
   *
   * @param id the unique identifier of the user
   * @param username the username of the user
   * @param email the email address of the user
   */
  public UserDTO(Long id, String username, String email) {
    this.id = id;
    this.username = username;
    this.email = email;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}