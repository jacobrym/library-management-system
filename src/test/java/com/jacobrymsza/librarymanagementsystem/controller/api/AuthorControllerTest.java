package com.jacobrymsza.librarymanagementsystem.controller.api;

import com.jacobrymsza.librarymanagementsystem.dto.AuthorDTO;
import com.jacobrymsza.librarymanagementsystem.exception.RestGlobalExceptionHandler;
import com.jacobrymsza.librarymanagementsystem.service.AuthorService;
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
class AuthorControllerTest {

  private MockMvc mockMvc;

  @Mock
  private AuthorService authorService;

  @InjectMocks
  private AuthorController authorController;

  @BeforeEach
  void setUp() {
    mockMvc = MockMvcBuilders.standaloneSetup(authorController)
        .setControllerAdvice(new RestGlobalExceptionHandler())
        .build();
  }

  @Test
  void getAllAuthors_success() throws Exception {
    AuthorDTO authorDTO = new AuthorDTO(1L, "John", "Doe");
    when(authorService.getAllAuthors()).thenReturn(Arrays.asList(authorDTO));

    mockMvc.perform(get("/api/authors")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(1L))
        .andExpect(jsonPath("$[0].firstName").value("John"))
        .andExpect(jsonPath("$[0].lastName").value("Doe"));

    verify(authorService, times(1)).getAllAuthors();
  }

  @Test
  void getAuthorById_success() throws Exception {
    AuthorDTO authorDTO = new AuthorDTO(1L, "John", "Doe");
    when(authorService.getAuthorById(1L)).thenReturn(authorDTO);

    mockMvc.perform(get("/api/authors/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.firstName").value("John"))
        .andExpect(jsonPath("$.lastName").value("Doe"));

    verify(authorService, times(1)).getAuthorById(1L);
  }

  @Test
  void createAuthor_success() throws Exception {
    AuthorDTO inputDTO = new AuthorDTO(null, "John", "Doe");
    AuthorDTO createdDTO = new AuthorDTO(1L, "John", "Doe");
    when(authorService.createAuthor(any(AuthorDTO.class))).thenReturn(createdDTO);

    String jsonRequest = "{\"firstName\":\"John\",\"lastName\":\"Doe\"}";

    mockMvc.perform(post("/api/authors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRequest))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.firstName").value("John"))
        .andExpect(jsonPath("$.lastName").value("Doe"));

    verify(authorService, times(1)).createAuthor(any(AuthorDTO.class));
  }

  @Test
  void updateAuthor_success() throws Exception {
    AuthorDTO inputDTO = new AuthorDTO(null, "Jane", "Doe");
    AuthorDTO updatedDTO = new AuthorDTO(1L, "Jane", "Doe");
    when(authorService.updateAuthor(eq(1L), any(AuthorDTO.class))).thenReturn(updatedDTO);

    String jsonRequest = "{\"firstName\":\"Jane\",\"lastName\":\"Doe\"}";

    mockMvc.perform(put("/api/authors/1")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRequest))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(1L))
        .andExpect(jsonPath("$.firstName").value("Jane"))
        .andExpect(jsonPath("$.lastName").value("Doe"));

    verify(authorService, times(1)).updateAuthor(eq(1L), any(AuthorDTO.class));
  }

  @Test
  void deleteAuthor_success() throws Exception {
    doNothing().when(authorService).deleteAuthor(1L);

    mockMvc.perform(delete("/api/authors/1")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isNoContent());

    verify(authorService, times(1)).deleteAuthor(1L);
  }

  @Test
  void getAuthorById_notFound() throws Exception {
    when(authorService.getAuthorById(999L)).thenThrow(new IllegalArgumentException("Author not found"));

    mockMvc.perform(get("/api/authors/999")
            .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("Author not found"));

    verify(authorService, times(1)).getAuthorById(999L);
  }

  @Test
  void createAuthor_invalidInput() throws Exception {
    String invalidJson = "{\"firstName\":\"\",\"lastName\":\"Doe\"}";

    mockMvc.perform(post("/api/authors")
            .contentType(MediaType.APPLICATION_JSON)
            .content(invalidJson))
        .andExpect(status().isBadRequest());
  }

  @Test
  void updateAuthor_notFound() throws Exception {
    String jsonRequest = "{\"firstName\":\"Jane\",\"lastName\":\"Doe\"}";
    when(authorService.updateAuthor(eq(999L), any(AuthorDTO.class)))
        .thenThrow(new IllegalArgumentException("Author not found"));

    mockMvc.perform(put("/api/authors/999")
            .contentType(MediaType.APPLICATION_JSON)
            .content(jsonRequest))
        .andExpect(status().isBadRequest())
        .andExpect(content().string("Author not found"));
  }
}