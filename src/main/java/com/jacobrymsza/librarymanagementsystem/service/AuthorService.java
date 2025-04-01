package com.jacobrymsza.librarymanagementsystem.service;

import com.jacobrymsza.librarymanagementsystem.dto.AuthorDTO;
import com.jacobrymsza.librarymanagementsystem.entity.Author;
import com.jacobrymsza.librarymanagementsystem.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing author-related business logic.
 * Provides methods to interact with {@link Author} entities.
 */
@Service
public class AuthorService {
  private final AuthorRepository authorRepository;

  @Autowired
  public AuthorService(AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  /**
   * Creates a new author in the library system.
   *
   * @param authorDTO the data transfer object containing author details
   * @return the created AuthorDTO with assigned ID
   */
  @Transactional
  public AuthorDTO createAuthor(AuthorDTO authorDTO) {
    Author author = new Author();
    author.setFirstName(authorDTO.getFirstName());
    author.setLastName(authorDTO.getLastName());

    Author savedAuthor = authorRepository.save(author);
    return mapToDTO(savedAuthor);
  }

  /**
   * Updates an existing author identified by the given ID.
   *
   * @param id the ID of the author to update
   * @param authorDTO the data transfer object with updated author details
   * @return the updated AuthorDTO
   * @throws IllegalArgumentException if the author with the specified ID is not found
   */
  @Transactional
  public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) {
    Author author = authorRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Author with ID "
                                                        + id + " not found"));
    author.setFirstName(authorDTO.getFirstName());
    author.setLastName(authorDTO.getLastName());

    Author updatedAuthor = authorRepository.save(author);
    return mapToDTO(updatedAuthor);
  }

  /**
   * Deletes an author from the library system by their ID.
   *
   * @param id the ID of the author to delete
   * @throws IllegalArgumentException if the author with the specified ID is not found
   */
  @Transactional
  public void deleteAuthor(Long id) {
    Author author = authorRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Author with ID "
                                                        + id + " not found"));
    authorRepository.delete(author);
  }

  /**
   * Retrieves a list of all authors from the database.
   *
   * @return a list of {@link AuthorDTO} objects
   */
  public List<AuthorDTO> getAllAuthors() {
    return authorRepository.findAll()
        .stream()
        .map(author -> mapToDTO(author))
        .collect(Collectors.toList());
  }

  /**
   * Retrieves an author by their unique identifier.
   * This method queries the repository to find an author with the specified ID.
   * If no author is found, it throws an {@link EntityNotFoundException} with a
   * descriptive message.
   *
   * @param authorId the unique identifier of the author to retrieve
   * @return the {@link AuthorDTO} object corresponding to the given ID
   * @throws EntityNotFoundException if no author with the specified ID exists in the database
   */
  public AuthorDTO getAuthorById(Long authorId) {
    return mapToDTO(authorRepository.findById(authorId)
        .orElseThrow(() -> new EntityNotFoundException("Author with ID "
                                                       + authorId + " not found")));
  }

  /**
   * Saves an author to the database.
   * This method persists the provided {@link Author} object to the database using the repository.
   * If the author already exists (based on its ID), it will be updated; otherwise, a new author
   * will be created.
   *
   * @param author the {@link Author} object to save
   */
  public void saveAuthor(Author author) {
    authorRepository.save(author);
  }

  private AuthorDTO mapToDTO(Author author) {
    return new AuthorDTO(
        author.getId(),
        author.getFirstName(),
        author.getLastName()
    );
  }
}
