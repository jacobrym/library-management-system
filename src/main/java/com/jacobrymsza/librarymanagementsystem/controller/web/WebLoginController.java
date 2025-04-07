package com.jacobrymsza.librarymanagementsystem.controller.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for handling requests to the root URL of the application.
 */
@Controller
public class WebLoginController {

  /**
   * Redirects requests to the root URL (/) to the books list page.
   *
   * @return a redirect to the /books endpoint
   */
  @GetMapping("/")
  public String redirectToBooks() {
    return "redirect:/books";
  }

  /**
   * Displays the login page for user authentication.
   *
   * @return the name of the Thymeleaf template for the login page
   */
  @GetMapping("/login")
  public String showLoginPage() {
    return "login";
  }
}