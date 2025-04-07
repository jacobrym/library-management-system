package com.jacobrymsza.librarymanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Controller for handling custom error pages in the library management system.
 */
@Controller
public class ErrorController {

  /**
   * Handles the access denied page for users without sufficient permissions.
   *
   * @return the name of the Thymeleaf template for the 403 error page
   */
  @GetMapping("/error/403")
  public String accessDenied() {
    return "error/403";
  }
}