package com.jacobrymsza.librarymanagementsystem.service;

import com.jacobrymsza.librarymanagementsystem.entity.Author;
import com.jacobrymsza.librarymanagementsystem.repository.AuthorRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
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
   * Retrieves a list of all authors from the database.
   *
   * @return a list of {@link Author} objects
   */
  public List<Author> getAllAuthors() {
    return authorRepository.findAll();
  }

  /**
   * Retrieves an author by their unique identifier.
   * This method queries the repository to find an author with the specified ID.
   * If no author is found, it throws an {@link EntityNotFoundException} with a
   * descriptive message.
   *
   * @param authorId the unique identifier of the author to retrieve
   * @return the {@link Author} object corresponding to the given ID
   * @throws EntityNotFoundException if no author with the specified ID exists in the database
   */
  public Author getAuthorById(Long authorId) {
    return authorRepository.findById(authorId)
            .orElseThrow(() -> new EntityNotFoundException("Author with ID "
                                                           + authorId + " not found"));
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
}
