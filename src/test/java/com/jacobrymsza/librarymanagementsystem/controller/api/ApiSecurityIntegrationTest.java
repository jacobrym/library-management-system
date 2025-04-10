package com.jacobrymsza.librarymanagementsystem.controller.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class ApiSecurityIntegrationTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  @WithMockUser(username = "user", roles = "USER")
  void userAccessBooks_success() throws Exception {
    mockMvc.perform(get("/api/books")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser(username = "user", roles = "USER")
  void userAccessBooksNew_forbidden() throws Exception {
    mockMvc.perform(post("/api/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"title\":\"Test\",\"isbn\":\"1234567890\",\"authorNames\":[\"John Doe\"]}"))
        .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(username = "user", roles = "USER")
  void userAccessBorrowings_success() throws Exception {
    mockMvc.perform(get("/api/borrowings")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  @WithMockUser(username = "admin", roles = "ADMIN")
  void adminAccessUsers_success() throws Exception {
    mockMvc.perform(get("/api/users")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  void unauthenticatedAccessBooks_redirectToLogin() throws Exception {
    mockMvc.perform(get("/api/books")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is3xxRedirection())
        .andExpect(redirectedUrlPattern("**/login"));
  }
}