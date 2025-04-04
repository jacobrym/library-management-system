package com.jacobrymsza.librarymanagementsystem.controller.api;

import com.jacobrymsza.librarymanagementsystem.dto.AuthorDTO;
import com.jacobrymsza.librarymanagementsystem.service.AuthorService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller for managing author-related operations in the library system.
 * Provides endpoints for creating, retrieving, updating, and deleting authors.
 */
@RestController
@RequestMapping("/api/authors")
public class AuthorController {
  private final AuthorService authorService;

  @Autowired
  public AuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  @GetMapping
  public ResponseEntity<List<AuthorDTO>> getAllAuthors() {
    List<AuthorDTO> authors = authorService.getAllAuthors();
    return ResponseEntity.ok(authors);
  }

  @GetMapping("/{id}")
  public ResponseEntity<AuthorDTO> getAuthorById(@PathVariable Long id) {
    AuthorDTO author = authorService.getAuthorById(id);
    return ResponseEntity.ok(author);
  }

  @PostMapping
  public ResponseEntity<AuthorDTO> createAuthor(@Valid @RequestBody AuthorDTO authorDTO) {
    AuthorDTO createdAuthor = authorService.createAuthor(authorDTO);
    return ResponseEntity.status(201).body(createdAuthor);
  }

  @PutMapping("/{id}")
  public ResponseEntity<AuthorDTO> updateAuthor(@PathVariable Long id,
                                                @Valid @RequestBody AuthorDTO authorDTO) {
    AuthorDTO updatedAuthor = authorService.updateAuthor(id, authorDTO);
    return ResponseEntity.ok(updatedAuthor);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
    authorService.deleteAuthor(id);
    return ResponseEntity.noContent().build();
  }
}
