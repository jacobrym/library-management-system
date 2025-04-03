package com.jacobrymsza.librarymanagementsystem.service;

import com.jacobrymsza.librarymanagementsystem.dto.AuthorDTO;
import com.jacobrymsza.librarymanagementsystem.entity.Author;
import com.jacobrymsza.librarymanagementsystem.repository.AuthorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthorServiceTest {

  @Mock
  private AuthorRepository authorRepository;

  @InjectMocks
  private AuthorService authorService;

  private Author testAuthor;

  @BeforeEach
  void setUp() {
    testAuthor = new Author("John", "Doe");
    testAuthor.setId(1L);
  }

  @Test
  void createAuthor_success() {
    AuthorDTO authorDTO = new AuthorDTO(null, "John", "Doe");
    when(authorRepository.save(any(Author.class))).thenReturn(testAuthor);

    AuthorDTO result = authorService.createAuthor(authorDTO);

    assertNotNull(result);
    assertEquals(1L, result.getId());
    assertEquals("John", result.getFirstName());
    assertEquals("Doe", result.getLastName());
    verify(authorRepository, times(1)).save(any(Author.class));
  }

  @Test
  void updateAuthor_success() {
    AuthorDTO authorDTO = new AuthorDTO(null, "Jane", "Doe");
    when(authorRepository.findById(1L)).thenReturn(Optional.of(testAuthor));
    when(authorRepository.save(any(Author.class))).thenReturn(testAuthor);

    AuthorDTO result = authorService.updateAuthor(1L, authorDTO);

    assertNotNull(result);
    assertEquals(1L, result.getId());
    verify(authorRepository, times(1)).findById(1L);
    verify(authorRepository, times(1)).save(any(Author.class));
  }

  @Test
  void updateAuthor_notFound_throwsException() {
    AuthorDTO authorDTO = new AuthorDTO(null, "Jane", "Doe");
    when(authorRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(IllegalArgumentException.class, () ->
        authorService.updateAuthor(1L, authorDTO));
    verify(authorRepository, times(1)).findById(1L);
    verify(authorRepository, never()).save(any(Author.class));
  }

  @Test
  void deleteAuthor_success() {
    when(authorRepository.findById(1L)).thenReturn(Optional.of(testAuthor));

    authorService.deleteAuthor(1L);

    verify(authorRepository, times(1)).findById(1L);
    verify(authorRepository, times(1)).delete(testAuthor);
  }

  @Test
  void deleteAuthor_notFound_throwsException() {
    when(authorRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(IllegalArgumentException.class, () ->
        authorService.deleteAuthor(1L));
    verify(authorRepository, times(1)).findById(1L);
    verify(authorRepository, never()).delete(any(Author.class));
  }

  @Test
  void getAllAuthors_success() {
    when(authorRepository.findAll()).thenReturn(Arrays.asList(testAuthor));

    List<AuthorDTO> result = authorService.getAllAuthors();

    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals("John", result.get(0).getFirstName());
    assertEquals("Doe", result.get(0).getLastName());
    verify(authorRepository, times(1)).findAll();
  }

  @Test
  void getAuthorById_success() {
    when(authorRepository.findById(1L)).thenReturn(Optional.of(testAuthor));

    AuthorDTO result = authorService.getAuthorById(1L);

    assertNotNull(result);
    assertEquals(1L, result.getId());
    assertEquals("John", result.getFirstName());
    assertEquals("Doe", result.getLastName());
    verify(authorRepository, times(1)).findById(1L);
  }

  @Test
  void getAuthorById_notFound_throwsException() {
    when(authorRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(jakarta.persistence.EntityNotFoundException.class, () ->
        authorService.getAuthorById(1L));
    verify(authorRepository, times(1)).findById(1L);
  }
}