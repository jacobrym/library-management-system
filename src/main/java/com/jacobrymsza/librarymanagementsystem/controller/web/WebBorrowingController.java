package com.jacobrymsza.librarymanagementsystem.controller.web;

import com.jacobrymsza.librarymanagementsystem.dto.BorrowRequestDTO;
import com.jacobrymsza.librarymanagementsystem.service.BookService;
import com.jacobrymsza.librarymanagementsystem.service.BorrowingService;
import com.jacobrymsza.librarymanagementsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * MVC Controller for managing borrowing-related operations in the library system.
 * Provides endpoints for viewing, creating, and returning borrowings via
 * Thymeleaf templates.
 */
@Controller
@RequestMapping("/borrowings")
public class WebBorrowingController {
  private final BorrowingService borrowingService;
  private final BookService bookService;
  private final UserService userService;

  /**
   * Constructs a new {@code WebBorrowingController} with the specified services.
   * This constructor uses Spring's dependency injection to provide access to the services
   * required for managing borrowings, books, and users in the library system.
   *
   * @param borrowingService the service for managing borrowing operations, must not be null
   * @param bookService the service for managing book operations, must not be null
   * @param userService the service for managing user operations, must not be null
   */
  @Autowired
  public WebBorrowingController(BorrowingService borrowingService,
                                BookService bookService,
                                UserService userService) {
    this.borrowingService = borrowingService;
    this.bookService = bookService;
    this.userService = userService;
  }

  /**
   * Displays a list of all borrowing records in the system.
   *
   * @param model the Spring MVC model to hold attributes for the view
   * @return the name of the Thymeleaf template for displaying the list of borrowings
   */
  @GetMapping
  public String getAllBorrowings(Model model) {
    model.addAttribute("borrowings", borrowingService.getAllBorrowings());
    return "borrowings/list";
  }

  /**
   * Shows the form for borrowing a book.
   *
   * @param model the Spring MVC model to hold attributes for the view
   * @return the name of the Thymeleaf template for the borrowing form
   */
  @GetMapping("/new")
  public String showBorrowForm(Model model) {
    model.addAttribute("borrowRequest", new BorrowRequestDTO());
    model.addAttribute("books", bookService.getAllBooks());
    model.addAttribute("users", userService.getAllUsers());
    return "borrowings/new";
  }

  /**
   * Handles the submission of the borrowing form.
   *
   * @param borrowRequestDTO the DTO containing borrowing request details
   * @param model the Spring MVC model to hold attributes for the view
   * @return a redirect to the borrowings list on success, or the form on error
   */
  @PostMapping("/new")
  public String borrowBook(@ModelAttribute("borrowRequest") BorrowRequestDTO borrowRequestDTO,
                           Model model) {
    try {
      borrowingService.borrowBook(borrowRequestDTO.getBookId(), borrowRequestDTO.getUserId());
      return "redirect:/borrowings";
    } catch (IllegalArgumentException e) {
      model.addAttribute("error", e.getMessage());
      model.addAttribute("books", bookService.getAllBooks());
      model.addAttribute("users", userService.getAllUsers());
      return "borrowings/new";
    }
  }

  /**
   * Handles the return of a borrowed book.
   *
   * @param id the ID of the borrowing to return
   * @param model the Spring MVC model to hold attributes for the view
   * @return a redirect to the borrowings list on success, or the list with an error message
   */
  @PostMapping("/{id}/return")
  public String returnBook(@PathVariable Long id, Model model) {
    try {
      borrowingService.returnBook(id);
      return "redirect:/borrowings";
    } catch (IllegalArgumentException e) {
      model.addAttribute("error", e.getMessage());
      model.addAttribute("borrowings", borrowingService.getAllBorrowings());
      return "borrowings/list";
    }
  }
}