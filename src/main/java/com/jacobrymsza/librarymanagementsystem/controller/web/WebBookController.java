package com.jacobrymsza.librarymanagementsystem.controller.web;

import com.jacobrymsza.librarymanagementsystem.dto.BookDTO;
import com.jacobrymsza.librarymanagementsystem.service.AuthorService;
import com.jacobrymsza.librarymanagementsystem.service.BookService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * MVC Controller for managing book-related operations in the library system.
 * Provides endpoints for viewing, creating, and displaying book details via
 * Thymeleaf templates.
 */
@Controller
@RequestMapping({"/", "/books"})
public class WebBookController {
  private final BookService bookService;
  private final AuthorService authorService; // Dodajemy AuthorService

  @Autowired
  public WebBookController(BookService bookService, AuthorService authorService) {
    this.bookService = bookService;
    this.authorService = authorService;
  }

  /**
   * Displays a list of all books.
   *
   * @param model the model to pass attributes to the view
   * @return the name of the Thymeleaf template for the books list
   */
  @GetMapping
  public String getAllBooks(Model model) {
    model.addAttribute("books", bookService.getAllBooks());
    return "books/list";
  }

  /**
   * Shows the form for creating a new book.
   *
   * @param model the model to pass attributes to the view
   * @return the name of the Thymeleaf template for the book creation form
   */
  @GetMapping("/new")
  public String showCreateForm(Model model) {
    model.addAttribute("book", new BookDTO());
    model.addAttribute("authors", authorService.getAllAuthors());
    return "books/new";
  }

  /**
   * Handles the submission of the new book form.
   *
   * @param bookDTO the DTO containing book details to be created
   * @param result the binding result for validation errors
   * @param model the Spring MVC model to hold attributes for the view
   * @return a redirect to the books list on success, or the form template
   *         if validation fails or an error occurs
   */
  @PostMapping("/new")
  public String createBook(@Valid @ModelAttribute("book") BookDTO bookDTO,
                           BindingResult result, Model model) {
    if (result.hasErrors()) {
      model.addAttribute("authors", authorService.getAllAuthors());
      return "books/new";
    }
    try {
      bookService.createBook(bookDTO);
      return "redirect:/books";
    } catch (IllegalArgumentException e) {
      model.addAttribute("error", e.getMessage());
      model.addAttribute("authors", authorService.getAllAuthors());
      return "books/new";
    }
  }

  /**
   * Displays details of a specific book identified by its ID.
   *
   * @param id the ID of the book to retrieve
   * @param model the Spring MVC model to hold attributes for the view
   * @return the name of the Thymeleaf template for displaying book details
   */
  @GetMapping("/{id}")
  public String getBookById(@PathVariable Long id, Model model) {
    model.addAttribute("book", bookService.getBookById(id));
    return "books/details";
  }
}