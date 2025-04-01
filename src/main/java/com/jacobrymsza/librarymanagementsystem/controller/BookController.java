package com.jacobrymsza.librarymanagementsystem.controller;

import com.jacobrymsza.librarymanagementsystem.dto.BookDTO;
import com.jacobrymsza.librarymanagementsystem.service.BookService;
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
 * REST controller for managing book-related operations in the library system.
 * Provides endpoints for creating, retrieving, updating, and deleting books.
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

  private final BookService bookService;

  @Autowired
  public BookController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping
  public ResponseEntity<List<BookDTO>> getAllBooks() {
    return ResponseEntity.ok(bookService.getAllBooks());
  }

  @PostMapping
  public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO) {
    BookDTO createdBook = bookService.createBook(bookDTO);
    return ResponseEntity.status(201).body(createdBook);
  }

  @PutMapping("/{id}")
  public ResponseEntity<BookDTO> updateBook(@PathVariable Long id,
                                            @Valid @RequestBody BookDTO bookDTO) {
    BookDTO updatedBook = bookService.updateBook(id, bookDTO);
    return ResponseEntity.ok(updatedBook);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
    bookService.deleteBook(id);
    return ResponseEntity.noContent().build();
  }
}
