package com.jacobrymsza.librarymanagementsystem.service;

import com.jacobrymsza.librarymanagementsystem.dto.UserDTO;
import com.jacobrymsza.librarymanagementsystem.entity.User;
import com.jacobrymsza.librarymanagementsystem.repository.UserRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing user-related business logic.
 */
@Service
public class UserService {
  private final UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  /**
   * Retrieves a list of all users in the system.
   *
   * @return a list of UserDTO objects
   */
  public List<UserDTO> getAllUsers() {
    return userRepository.findAll().stream()
        .map(this::mapToDTO)
        .collect(Collectors.toList());
  }

  /**
   * Retrieves a user by their unique identifier.
   *
   * @param userId the ID of the user to retrieve
   * @return the UserDTO object
   * @throws IllegalArgumentException if user with the specified ID is not found
   */
  public UserDTO getUserById(Long userId) {
    User user = userRepository.findById(userId)
        .orElseThrow(() -> new IllegalArgumentException("User with ID " + userId + " not found"));
    return mapToDTO(user);
  }

  /**
   * Creates a new user in the system.
   *
   * @param userDTO the data transfer object containing user details
   * @return the created UserDTO with assigned ID
   */
  @Transactional
  public UserDTO createUser(UserDTO userDTO) {
    User user = new User();
    user.setUsername(userDTO.getUsername());
    user.setEmail(userDTO.getEmail());
    User savedUser = userRepository.save(user);
    return mapToDTO(savedUser);
  }

  /**
   * Updates an existing user identified by the given ID.
   *
   * @param id the ID of the user to update
   * @param userDTO the data transfer object with updated user details
   * @return the updated UserDTO
   * @throws IllegalArgumentException if user with the specified ID is not found
   */
  @Transactional
  public UserDTO updateUser(Long id, UserDTO userDTO) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("User with ID " + id + " not found"));
    user.setUsername(userDTO.getUsername());
    user.setEmail(userDTO.getEmail());
    User updatedUser = userRepository.save(user);
    return mapToDTO(updatedUser);
  }

  /**
   * Deletes a user from the system by their ID.
   *
   * @param id the ID of the user to delete
   * @throws IllegalArgumentException if user with the specified ID is not found
   */
  @Transactional
  public void deleteUser(Long id) {
    User user = userRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("User with ID " + id + " not found"));
    userRepository.delete(user);
  }

  private UserDTO mapToDTO(User user) {
    return new UserDTO(
        user.getId(),
        user.getUsername(),
        user.getEmail()
    );
  }
}