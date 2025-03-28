package com.jacobrymsza.librarymanagementsystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

/**
 * Represents the borrowing details of a user in the library management system.
 * This entity stores information related to a user's borrowing activities, including
 * their unique identifier, associated user details, and contact phone number.
 * The data is persisted in the "borrowing_details" table in the database.
 */
@Entity
@Table(name = "borrowing_details")
public class BorrowingDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;

  @OneToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "user_id", nullable = false, unique = true)
  private User user;

  @Column(name = "contact_phone", nullable = false)
  private String contactPhone;

  public BorrowingDetails() {
  }

  public BorrowingDetails(User user, String contactPhone) {
    this.user = user;
    this.contactPhone = contactPhone;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public String getContactPhone() {
    return contactPhone;
  }

  public void setContactPhone(String contactPhone) {
    this.contactPhone = contactPhone;
  }
}
