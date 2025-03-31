package com.jacobrymsza.librarymanagementsystem.service;

import com.jacobrymsza.librarymanagementsystem.dto.BookDTO;
import com.jacobrymsza.librarymanagementsystem.entity.Book;
import com.jacobrymsza.librarymanagementsystem.repository.BookRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing {@link Book} entities.
 * Handles business logic related to books in the library system.
 */
@Service
public class BookService {
  private final BookRepository bookRepository;

  @Autowired
  public BookService(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  /**
   * Retrieves a list of all books in the library system.
   * This method fetches all book entities from the data source, converts them into
   * {@link BookDTO} objects, and returns them as a list. Each {@link BookDTO} contains
   * basic book information (ID, title, ISBN) along with a list of author names.
   *
   * @return a {@link List} of {@link BookDTO} objects representing all books in the system
   */
  public List<BookDTO> getAllBooks() {
    return bookRepository.findAll().stream()
            .map(book -> new BookDTO(
                    book.getId(),
                    book.getTitle(),
                    book.getIsbn(),
                    book.getAuthors().stream()
                            .map(author -> author.getFirstName() + " " + author.getLastName())
                            .toList()
            ))
            .toList();
  }

  /**
   * Retrieves a book by its unique identifier.
   *
   * @param bookId the unique identifier of the book to retrieve
   * @return the {@link Book} object associated with the given ID
   * @throws IllegalArgumentException if no book with the specified ID is found
   */
  public Book getBookById(Long bookId) {
    return bookRepository.findById(bookId)
            .orElseThrow(() -> new IllegalArgumentException("Book with ID "
                                                            + bookId + " not found"));
  }

  /**
   * Retrieves a book by its ISBN number.
   *
   * @param isbn the ISBN number of the book to retrieve
   * @return the {@link Book} object associated with the given ISBN,
   *         or {@code null} if no book is found
   */
  public Book getBookByIsbn(String isbn) {
    return bookRepository.findByIsbn(isbn);
  }

  /**
   * Saves a book to the repository.
   *
   * @param book the {@link Book} object to be saved
   */
  public void saveBook(Book book) {
    bookRepository.save(book);
  }
}
