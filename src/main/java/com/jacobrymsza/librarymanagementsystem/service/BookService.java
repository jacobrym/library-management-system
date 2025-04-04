package com.jacobrymsza.librarymanagementsystem.service;

import com.jacobrymsza.librarymanagementsystem.dto.BookDTO;
import com.jacobrymsza.librarymanagementsystem.entity.Author;
import com.jacobrymsza.librarymanagementsystem.entity.Book;
import com.jacobrymsza.librarymanagementsystem.repository.AuthorRepository;
import com.jacobrymsza.librarymanagementsystem.repository.BookRepository;
import jakarta.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing {@link Book} entities.
 * Handles business logic related to books in the library system.
 */
@Service
public class BookService {
  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;

  @Autowired
  public BookService(BookRepository bookRepository, AuthorRepository authorRepository) {
    this.bookRepository = bookRepository;
    this.authorRepository = authorRepository;
  }

  /**
   * Creates a new book in the library system.
   *
   * @param bookDTO the data transfer object containing book details
   * @return the created BookDTO with assigned ID
   * @throws IllegalArgumentException if no authors are provided or specified authors are not found
   */
  @Transactional
  public BookDTO createBook(BookDTO bookDTO) {
    Book book = new Book();
    book.setTitle(bookDTO.getTitle());
    book.setIsbn(bookDTO.getIsbn());

    List<Author> authors = mapAuthorNamesToEntities(bookDTO.getAuthorNames());
    if (authors.isEmpty()) {
      throw new IllegalArgumentException("Book must have at least one author");
    }

    for (Author author : authors) {
      book.addAuthor(author);
    }

    Book updatedBook = bookRepository.save(book);
    return mapToDTO(updatedBook);
  }

  /**
   * Updates an existing book identified by the given ID.
   *
   * @param id the ID of the book to update
   * @param bookDTO the data transfer object with updated book details
   * @return the updated BookDTO
   * @throws IllegalArgumentException if the book or specified authors are not found
   */
  @Transactional
  public BookDTO updateBook(Long id, BookDTO bookDTO) {
    Book book = bookRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Book with ID " + id + " not found"));

    book.setTitle(bookDTO.getTitle());
    book.setIsbn(bookDTO.getIsbn());

    List<Author> authors = mapAuthorNamesToEntities(bookDTO.getAuthorNames());
    if (authors.isEmpty()) {
      throw new IllegalArgumentException("Book must have at least one author");
    }
    book.setAuthors(authors);

    Book updatedBook = bookRepository.save(book);

    return mapToDTO(updatedBook);
  }

  /**
   * Deletes a book from the library system by its ID.
   *
   * @param id the ID of the book to delete
   * @throws IllegalArgumentException if the book with the specified ID is not found
   */
  @Transactional
  public void deleteBook(Long id) {
    Book book = bookRepository.findById(id)
        .orElseThrow(() -> new IllegalArgumentException("Book with ID " + id + " not found"));
    bookRepository.delete(book);
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
   * @return the {@link BookDTO} object associated with the given ID
   * @throws IllegalArgumentException if no book with the specified ID is found
   */
  public BookDTO getBookById(Long bookId) {
    return mapToDTO(bookRepository.findById(bookId)
        .orElseThrow(() -> new IllegalArgumentException("Book with ID "
            + bookId + " not found")));
  }

  /**
   * Retrieves a book by its ISBN number.
   *
   * @param isbn the ISBN number of the book to retrieve
   * @return the {@link BookDTO} object associated with the given ISBN,
   *         or {@code null} if no book is found
   */
  public BookDTO getBookByIsbn(String isbn) {
    return mapToDTO(bookRepository.findByIsbn(isbn));
  }

  /**
   * Saves a book to the repository.
   *
   * @param book the {@link Book} object to be saved
   */
  public void saveBook(Book book) {
    bookRepository.save(book);
  }

  private List<Author> mapAuthorNamesToEntities(List<String> authorNames) {
    List<Author> authors = new ArrayList<>();
    if (authorNames != null) {
      for (String fullName : authorNames) {
        String[] parts = fullName.trim().split("\\s+");
        String firstName = parts[0];
        String lastName = parts.length > 1 ? parts[1] : "";
        Author author = authorRepository.findByFirstNameAndLastName(firstName, lastName)
            .orElseThrow(() -> new IllegalArgumentException("Author " + fullName + " not found"));
        authors.add(author);
      }
    }
    return authors;
  }

  private BookDTO mapToDTO(Book book) {
    List<String> authorNames = book.getAuthors().stream()
        .map(author -> author.getFirstName() + " " + author.getLastName())
        .collect(Collectors.toList());
    return new BookDTO(
        book.getId(),
        book.getTitle(),
        book.getIsbn(),
        authorNames
    );
  }
}
