package com.jacobrymsza.librarymanagementsystem.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller class that handles HTTP requests for basic greetings.
 * This class provides a simple endpoint to test the application.
 */
@RestController
public class HelloController {

  @GetMapping("/hello")
  public String helloPage() {
    return "Hello World";
  }
}
