package com.jacobrymsza.librarymanagementsystem.controller.api;

import com.jacobrymsza.librarymanagementsystem.dto.BookDTO;
import com.jacobrymsza.librarymanagementsystem.exception.RestGlobalExceptionHandler;
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
    mockMvc = MockMvcBuilders.standaloneSetup(bookController)
        .setControllerAdvice(new RestGlobalExceptionHandler())
        .build();
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

  @Test
  void updateBook_success() throws Exception {
    BookDTO updatedBook = new BookDTO(1L, "Updated Book", "1234567890", Arrays.asList("John Doe"));
    when(bookService.updateBook(eq(1L), any(BookDTO.class))).thenReturn(updatedBook);

    String jsonRequest = "{\"title\":\"Updated Book\",\"isbn\":\"1234567890\",\"authorNames\":[\"John Doe\"]}";

    mockMvc.perform(put("/api/books/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRequest))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.title").value("Updated Book"));

    verify(bookService, times(1)).updateBook(eq(1L), any(BookDTO.class));
  }

  @Test
  void deleteBook_success() throws Exception {
    doNothing().when(bookService).deleteBook(1L);

    mockMvc.perform(delete("/api/books/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());

    verify(bookService, times(1)).deleteBook(1L);
  }

  @Test
  void deleteBook_notFound() throws Exception {
    doThrow(new IllegalArgumentException("Book not found")).when(bookService).deleteBook(999L);

    mockMvc.perform(delete("/api/books/999")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("Book not found"));
  }

  @Test
  void createBook_invalidIsbn() throws Exception {
    String invalidJson = "{\"title\":\"Test Book\",\"isbn\":\"abc\",\"authorNames\":[\"John Doe\"]}";

    mockMvc.perform(post("/api/books")
            .contentType(MediaType.APPLICATION_JSON)
            .content(invalidJson))
        .andExpect(status().isBadRequest());
  }
}