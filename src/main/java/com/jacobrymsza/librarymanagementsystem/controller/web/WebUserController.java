package com.jacobrymsza.librarymanagementsystem.controller.web;

import com.jacobrymsza.librarymanagementsystem.dto.UserDTO;
import com.jacobrymsza.librarymanagementsystem.service.UserService;
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
 * MVC Controller for managing user-related operations in the library system.
 * Provides endpoints for viewing, creating, and displaying user details via
 * Thymeleaf templates.
 */
@Controller
@RequestMapping("/users")
public class WebUserController {

  private final UserService userService;

  @Autowired
  public WebUserController(UserService userService) {
    this.userService = userService;
  }

  /**
   * Displays a list of all users in the system.
   *
   * @param model the Spring MVC model to hold attributes for the view
   * @return the name of the Thymeleaf template for displaying the list of users
   */
  @GetMapping
  public String getAllUsers(Model model) {
    model.addAttribute("users", userService.getAllUsers());
    return "users/list";
  }

  /**
   * Shows the form for creating a new user.
   *
   * @param model the Spring MVC model to hold attributes for the view
   * @return the name of the Thymeleaf template for the user creation form
   */
  @GetMapping("/new")
  public String showCreateForm(Model model) {
    model.addAttribute("user", new UserDTO());
    return "users/new";
  }

  /**
   * Handles the submission of the new user form.
   *
   * @param userDTO the DTO containing user details to be created
   * @param result the binding result for validation errors
   * @param model the Spring MVC model to hold attributes for the view
   * @return a redirect to the users list on success, or the form template
   *         if validation fails or an error occurs
   */
  @PostMapping("/new")
  public String createUser(@Valid @ModelAttribute("user") UserDTO userDTO,
                           BindingResult result, Model model) {
    if (result.hasErrors()) {
      return "users/new";
    }
    try {
      userService.createUser(userDTO);
      return "redirect:/users";
    } catch (IllegalArgumentException e) {
      model.addAttribute("error", e.getMessage());
      return "users/new";
    }
  }

  /**
   * Displays details of a specific user identified by their ID.
   *
   * @param id the ID of the user to retrieve
   * @param model the Spring MVC model to hold attributes for the view
   * @return the name of the Thymeleaf template for displaying user details,
   *         or the list template if an error occurs
   */
  @GetMapping("/{id}")
  public String getUserById(@PathVariable Long id, Model model) {
    try {
      model.addAttribute("user", userService.getUserById(id));
      return "users/details";
    } catch (IllegalArgumentException e) {
      model.addAttribute("error", e.getMessage());
      return "users/list"; // Wracamy do listy z błędem
    }
  }
}