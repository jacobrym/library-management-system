package com.jacobrymsza.librarymanagementsystem.service;

import com.jacobrymsza.librarymanagementsystem.dto.BookDTO;
import com.jacobrymsza.librarymanagementsystem.entity.Author;
import com.jacobrymsza.librarymanagementsystem.entity.Book;
import com.jacobrymsza.librarymanagementsystem.repository.AuthorRepository;
import com.jacobrymsza.librarymanagementsystem.repository.BookRepository;
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
class BookServiceTest {

  @Mock
  private BookRepository bookRepository;

  @Mock
  private AuthorRepository authorRepository;

  @InjectMocks
  private BookService bookService;

  private Book testBook;
  private Author testAuthor;

  @BeforeEach
  void setUp() {
    testAuthor = new Author("John", "Doe");
    testAuthor.setId(1L);

    testBook = new Book("Test Book", "1234567890");
    testBook.setId(1L);
    testBook.setAuthors(Arrays.asList(testAuthor));
  }

  @Test
  void createBook_success() {
    BookDTO bookDTO = new BookDTO(null, "Test Book", "1234567890",
        Arrays.asList("John Doe"));

    when(authorRepository.findByFirstNameAndLastName("John", "Doe"))
        .thenReturn(Optional.of(testAuthor));
    when(bookRepository.save(any(Book.class))).thenReturn(testBook);

    BookDTO result = bookService.createBook(bookDTO);

    assertNotNull(result);
    assertEquals("Test Book", result.getTitle());
    assertEquals("1234567890", result.getIsbn());
    assertEquals(1, result.getAuthorNames().size());
    verify(bookRepository, times(1)).save(any(Book.class));
  }

  @Test
  void createBook_noAuthors_throwsException() {
    BookDTO bookDTO = new BookDTO(null, "Test Book", "1234567890",
        Arrays.asList());

    assertThrows(IllegalArgumentException.class, () -> bookService.createBook(bookDTO));
  }

  @Test
  void getAllBooks_success() {
    when(bookRepository.findAll()).thenReturn(Arrays.asList(testBook));

    List<BookDTO> result = bookService.getAllBooks();

    assertNotNull(result);
    assertEquals(1, result.size());
    assertEquals("Test Book", result.get(0).getTitle());
    verify(bookRepository, times(1)).findAll();
  }

  @Test
  void getBookById_success() {
    when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));

    BookDTO result = bookService.getBookById(1L);

    assertNotNull(result);
    assertEquals("Test Book", result.getTitle());
    verify(bookRepository, times(1)).findById(1L);
  }

  @Test
  void getBookById_notFound_throwsException() {
    when(bookRepository.findById(1L)).thenReturn(Optional.empty());

    assertThrows(IllegalArgumentException.class, () -> bookService.getBookById(1L));
  }
}