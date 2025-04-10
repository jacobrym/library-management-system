package com.jacobrymsza.librarymanagementsystem.service;

import com.jacobrymsza.librarymanagementsystem.dto.BorrowingDTO;
import com.jacobrymsza.librarymanagementsystem.entity.Book;
import com.jacobrymsza.librarymanagementsystem.entity.User;
import com.jacobrymsza.librarymanagementsystem.repository.BookRepository;
import com.jacobrymsza.librarymanagementsystem.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class BorrowingServiceIntegrationTest {

  @Autowired
  private BorrowingService borrowingService;

  @Autowired
  private BookRepository bookRepository;

  @Autowired
  private UserRepository userRepository;

  @Test
  void borrowAndReturnBook_success() {
    Book book = new Book("Test Book", "1234567890");
    bookRepository.save(book);
    User user = new User("testuser", "test@example.com");
    userRepository.save(user);

    BorrowingDTO borrowing = borrowingService.borrowBook(book.getId(), user.getId());
    assertNotNull(borrowing.getBorrowDate());
    assertNull(borrowing.getReturnDate());

    assertFalse(borrowingService.isBookAvailable(book.getId()));

    BorrowingDTO returnedBorrowing = borrowingService.returnBook(borrowing.getId());
    assertNotNull(returnedBorrowing.getReturnDate());

    assertTrue(borrowingService.isBookAvailable(book.getId()));
  }
}