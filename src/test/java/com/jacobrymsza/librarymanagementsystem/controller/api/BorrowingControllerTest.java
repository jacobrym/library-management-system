package com.jacobrymsza.librarymanagementsystem.controller.api;

import com.jacobrymsza.librarymanagementsystem.dto.BorrowRequestDTO;
import com.jacobrymsza.librarymanagementsystem.dto.BorrowingDTO;
import com.jacobrymsza.librarymanagementsystem.service.BorrowingService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class BorrowingControllerTest {

  private MockMvc mockMvc;

  @Mock
  private BorrowingService borrowingService;

  @InjectMocks
  private BorrowingController borrowingController;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(borrowingController).build();
  }

  @Test
  void borrowBook_success() throws Exception {
    BorrowRequestDTO requestDTO = new BorrowRequestDTO();
    requestDTO.setBookId(1L);
    requestDTO.setUserId(1L);

    BorrowingDTO borrowingDTO = new BorrowingDTO(
        1L, "Test Book", "testuser", LocalDateTime.now(), null
    );

    when(borrowingService.borrowBook(1L, 1L)).thenReturn(borrowingDTO);

    String jsonRequest = "{\"bookId\":1,\"userId\":1}";

    mockMvc.perform(post("/api/borrowings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRequest))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.bookTitle").value("Test Book"))
        .andExpect(jsonPath("$.userUsername").value("testuser"));

    verify(borrowingService, times(1)).borrowBook(1L, 1L);
  }

  @Test
  void returnBook_success() throws Exception {
    BorrowingDTO borrowingDTO = new BorrowingDTO(
        1L, "Test Book", "testuser", LocalDateTime.now().minusDays(1), LocalDateTime.now()
    );

    when(borrowingService.returnBook(1L)).thenReturn(borrowingDTO);

    mockMvc.perform(put("/api/borrowings/1/return")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.bookTitle").value("Test Book"))
        .andExpect(jsonPath("$.userUsername").value("testuser"));

    verify(borrowingService, times(1)).returnBook(1L);
  }

  @Test
  void getBorrowingById_success() throws Exception {
    BorrowingDTO borrowingDTO = new BorrowingDTO(
        1L, "Test Book", "testuser", LocalDateTime.now().minusDays(1), null
    );

    when(borrowingService.getBorrowingById(1L)).thenReturn(borrowingDTO);

    mockMvc.perform(get("/api/borrowings/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.bookTitle").value("Test Book"))
        .andExpect(jsonPath("$.userUsername").value("testuser"));

    verify(borrowingService, times(1)).getBorrowingById(1L);
  }

  @Test
  void borrowBook_invalidRequest_returnsBadRequest() throws Exception {
    String invalidJsonRequest = "{}";

    mockMvc.perform(post("/api/borrowings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(invalidJsonRequest))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("Book ID and User ID are required"));

    verify(borrowingService, never()).borrowBook(any(), any());
  }

  @Test
  void returnBook_serviceThrowsException_returnsBadRequest() throws Exception {
    when(borrowingService.returnBook(1L))
        .thenThrow(new IllegalArgumentException("Borrowing not found"));

    mockMvc.perform(put("/api/borrowings/1/return")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("Borrowing not found"));

    verify(borrowingService, times(1)).returnBook(1L);
  }

  @Test
  void borrowBook_bookNotAvailable() throws Exception {
    String jsonRequest = "{\"bookId\":1,\"userId\":1}";
    when(borrowingService.borrowBook(1L, 1L))
        .thenThrow(new IllegalArgumentException("Book is currently borrowed"));

    mockMvc.perform(post("/api/borrowings")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRequest))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("Book is currently borrowed"));
  }

  @Test
  void returnBook_notFound() throws Exception {
    when(borrowingService.returnBook(999L))
        .thenThrow(new IllegalArgumentException("Borrowing not found"));

    mockMvc.perform(put("/api/borrowings/999/return")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("Borrowing not found"));
  }
}