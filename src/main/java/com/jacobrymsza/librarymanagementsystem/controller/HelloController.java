package com.jacobrymsza.librarymanagementsystem.controller;

import com.jacobrymsza.librarymanagementsystem.dto.BookDTO;
import com.jacobrymsza.librarymanagementsystem.service.BookService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class that handles HTTP requests for basic greetings.
 * This class provides a simple endpoint to test the application.
 */
@RestController
public class HelloController {

  private final BookService bookService;

  @Autowired
  public HelloController(BookService bookService) {
    this.bookService = bookService;
  }

  @GetMapping("/hello")
  public String helloPage() {
    return "Hello World";
  }

  @GetMapping("/books")
  public List<BookDTO> getAllBooks() {
    return bookService.getAllBooks();
  }
}
