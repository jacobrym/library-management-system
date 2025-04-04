package com.jacobrymsza.librarymanagementsystem.controller.api;

import com.jacobrymsza.librarymanagementsystem.dto.BookDTO;
import com.jacobrymsza.librarymanagementsystem.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

  private MockMvc mockMvc;

  @Mock
  private BookService bookService;

  @InjectMocks
  private BookController bookController;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(bookController).build();
  }

  @Test
  void getAllBooks_success() throws Exception {
    BookDTO bookDTO = new BookDTO(1L, "Test Book", "1234567890", Arrays.asList("John Doe"));
    when(bookService.getAllBooks()).thenReturn(Arrays.asList(bookDTO));

    mockMvc.perform(get("/api/books")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].title").value("Test Book"))
        .andExpect(jsonPath("$[0].isbn").value("1234567890"));

    verify(bookService, times(1)).getAllBooks();
  }

  @Test
  void createBook_success() throws Exception {
    BookDTO bookDTO = new BookDTO(null, "Test Book", "1234567890", Arrays.asList("John Doe"));
    BookDTO createdBook = new BookDTO(1L, "Test Book", "1234567890", Arrays.asList("John Doe"));
    when(bookService.createBook(any(BookDTO.class))).thenReturn(createdBook);

    String jsonRequest = "{\"title\":\"Test Book\",\"isbn\":\"1234567890\",\"authorNames\":[\"John Doe\"]}";

    mockMvc.perform(post("/api/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRequest))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.title").value("Test Book"));

    verify(bookService, times(1)).createBook(any(BookDTO.class));
  }
}