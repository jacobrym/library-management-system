package com.jacobrymsza.librarymanagementsystem.exception;

import java.nio.file.AccessDeniedException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authorization.AuthorizationDeniedException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Global exception handler for the web layer of the library management system.
 * This class provides centralized exception handling for all controllers,
 * rendering appropriate error pages based on the type of exception encountered.
 */
@ControllerAdvice
public class WebGlobalExceptionHandler {
  private static final Logger logger = LoggerFactory.getLogger(WebGlobalExceptionHandler.class);

  /**
   * Handles AuthorizationDeniedException and renders a 403 error page.
   * This method is triggered when a user attempts to access a resource without sufficient
   * permissions, typically due to @PreAuthorize restrictions.
   *
   * @param exception the AuthorizationDeniedException thrown during the request
   * @param model the Model object to pass attributes to the view
   * @return the name of the Thymeleaf template for the 403 error page ("error/error")
   */
  @ExceptionHandler(AuthorizationDeniedException.class)
  public String handleAuthorizationDeniedException(AuthorizationDeniedException exception,
                                                   Model model) {
    logger.warn("Authorization denied: {}", exception.getMessage());
    model.addAttribute("title", "Access Denied");
    model.addAttribute("error", "You do not have permission to access this page.");
    model.addAttribute("extraMessage",
                       "Please contact an administrator if you believe this is an error.");
    model.addAttribute("isAccessDenied", true);
    return "error/error";
  }

  /**
   * Handles AccessDeniedException and renders a 403 error page.
   * This method is triggered when a user attempts to access a resource without sufficient
   * permissions.
   *
   * @param exception the AccessDeniedException thrown during the request
   * @param model the Model object to pass attributes to the view
   * @return the name of the Thymeleaf template for the 403 error page ("error/error")
   */
  @ExceptionHandler(AccessDeniedException.class)
  public String handleAccessDeniedException(AccessDeniedException exception, Model model) {
    logger.warn("Access denied: {}", exception.getMessage());
    model.addAttribute("title", "Access Denied");
    model.addAttribute("error", "You do not have permission to access this page.");
    model.addAttribute("extraMessage",
                       "Please contact an administrator if you believe this is an error.");
    model.addAttribute("isAccessDenied", true); // Flaga dla przycisku Logout
    return "error/error";
  }

  /**
   * Handles IllegalArgumentException and renders a general error page.
   * This method is triggered when an invalid argument is passed to a method or service.
   *
   * @param exception the IllegalArgumentException thrown during the request
   * @param model the Model object to pass attributes to the view
   * @return the name of the Thymeleaf template for the general error page ("error/error")
   */
  @ExceptionHandler(IllegalArgumentException.class)
  public String handleIllegalArgumentException(IllegalArgumentException exception, Model model) {
    logger.error("Illegal argument: {}", exception.getMessage());
    model.addAttribute("title", "Error");
    model.addAttribute("error", "Invalid input: " + exception.getMessage());
    model.addAttribute("isAccessDenied", false);
    return "error/error";
  }

  /**
   * Handles general exceptions and renders a general error page.
   * This method serves as a catch-all for unhandled exceptions in the application.
   *
   * @param exception the Exception thrown during the request
   * @param model the Model object to pass attributes to the view
   * @return the name of the Thymeleaf template for the general error page ("error/error")
   */
  @ExceptionHandler(Exception.class)
  public String handleGeneralException(Exception exception, Model model) {
    logger.error("Unexpected error: {}", exception.getMessage(), exception);
    model.addAttribute("title", "Error");
    model.addAttribute("error", "An unexpected error occurred: " + exception.getMessage());
    model.addAttribute("isAccessDenied", false);
    return "error/error";
  }
}
