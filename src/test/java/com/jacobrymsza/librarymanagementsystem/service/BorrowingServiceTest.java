package com.jacobrymsza.librarymanagementsystem.service;

import com.jacobrymsza.librarymanagementsystem.dto.BorrowingDTO;
import com.jacobrymsza.librarymanagementsystem.entity.Book;
import com.jacobrymsza.librarymanagementsystem.entity.Borrowing;
import com.jacobrymsza.librarymanagementsystem.entity.User;
import com.jacobrymsza.librarymanagementsystem.repository.BookRepository;
import com.jacobrymsza.librarymanagementsystem.repository.BorrowingRepository;
import com.jacobrymsza.librarymanagementsystem.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BorrowingServiceTest {

  @Mock
  private BorrowingRepository borrowingRepository;

  @Mock
  private BookRepository bookRepository;

  @Mock
  private UserRepository userRepository;

  @InjectMocks
  private BorrowingService borrowingService;

  private Book testBook;
  private User testUser;
  private Borrowing testBorrowing;

  @BeforeEach
  void setUp() {
    testBook = new Book("Test Book", "1234567890");
    testBook.setId(1L);

    testUser = new User("testuser", "test@example.com");
    testUser.setId(1L);

    testBorrowing = new Borrowing(testBook, testUser, LocalDateTime.now());
    testBorrowing.setId(1L);
  }

  @Test
  void borrowBook_success() {
    when(bookRepository.findById(1L)).thenReturn(Optional.of(testBook));
    when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));
    when(borrowingRepository.save(any(Borrowing.class))).thenReturn(testBorrowing);

    BorrowingDTO result = borrowingService.borrowBook(1L, 1L);

    assertNotNull(result);
    assertEquals("Test Book", result.getBookTitle());
    assertEquals("testuser", result.getUserUsername());
    assertNotNull(result.getBorrowDate());
    verify(borrowingRepository, times(1)).save(any(Borrowing.class));
  }

  @Test
  void returnBook_success() {
    when(borrowingRepository.findById(1L)).thenReturn(Optional.of(testBorrowing));
    when(borrowingRepository.save(any(Borrowing.class))).thenReturn(testBorrowing);

    BorrowingDTO result = borrowingService.returnBook(1L);

    assertNotNull(result);
    assertNotNull(result.getReturnDate());
    verify(borrowingRepository, times(1)).save(any(Borrowing.class));
  }

  @Test
  void returnBook_alreadyReturned_throwsException() {
    testBorrowing.setReturnDate(LocalDateTime.now());
    when(borrowingRepository.findById(1L)).thenReturn(Optional.of(testBorrowing));

    assertThrows(IllegalArgumentException.class, () -> borrowingService.returnBook(1L));
  }
}