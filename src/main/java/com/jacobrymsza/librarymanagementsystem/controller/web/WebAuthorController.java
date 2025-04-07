package com.jacobrymsza.librarymanagementsystem.controller.web;

import com.jacobrymsza.librarymanagementsystem.dto.AuthorDTO;
import com.jacobrymsza.librarymanagementsystem.service.AuthorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * MVC Controller for managing author-related operations in the library system.
 * Provides endpoints for viewing, creating, and displaying author details via
 * Thymeleaf templates.
 */
@Controller
@RequestMapping("/authors")
public class WebAuthorController {
  private final AuthorService authorService;

  @Autowired
  public WebAuthorController(AuthorService authorService) {
    this.authorService = authorService;
  }

  /**
   * Displays a list of all authors in the system.
   *
   * @param model the Spring MVC model to hold attributes for the view
   * @return the name of the Thymeleaf template for displaying the list of authors
   */
  @GetMapping
  public String getAllAuthors(Model model) {
    model.addAttribute("authors", authorService.getAllAuthors());
    return "authors/list";
  }

  /**
   * Shows the form for creating a new author.
   *
   * @param model the Spring MVC model to hold attributes for the view
   * @return the name of the Thymeleaf template for the author creation form
   */
  @GetMapping("/new")
  @PreAuthorize("hasRole('ADMIN')")
  public String showCreateAuthorForm(Model model) {
    model.addAttribute("author", new AuthorDTO());
    return "authors/new";
  }

  /**
   * Handles the submission of the new author form.
   *
   * @param authorDTO the DTO containing author details to be created
   * @param result the binding result for validation errors
   * @return a redirect to the authors list on success, or the form template if validation fails
   */
  @PostMapping("/new")
  @PreAuthorize("hasRole('ADMIN')")
  public String createAuthor(@Valid @ModelAttribute("author") AuthorDTO authorDTO,
                             BindingResult result) {
    if (result.hasErrors()) {
      return "authors/new";
    }
    authorService.createAuthor(authorDTO);
    return "redirect:/authors";
  }

  /**
   * Displays details of a specific author identified by their ID.
   *
   * @param id the ID of the author to retrieve
   * @param model the Spring MVC model to hold attributes for the view
   * @return the name of the Thymeleaf template for displaying author details
   */
  @GetMapping("/{id}")
  public String getAuthorById(@PathVariable Long id, Model model) {
    model.addAttribute("author", authorService.getAuthorById(id));
    return "authors/details";
  }
}
