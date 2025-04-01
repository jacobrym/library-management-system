memberSearchIndex = [{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Author","l":"Author()","u":"%3Cinit%3E()"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Author","l":"Author(String, String)","u":"%3Cinit%3E(java.lang.String,java.lang.String)"},{"p":"com.jacobrymsza.librarymanagementsystem.controller","c":"AuthorController","l":"AuthorController(AuthorService)","u":"%3Cinit%3E(com.jacobrymsza.librarymanagementsystem.service.AuthorService)"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"AuthorDTO","l":"AuthorDTO(Long, String, String)","u":"%3Cinit%3E(java.lang.Long,java.lang.String,java.lang.String)"},{"p":"com.jacobrymsza.librarymanagementsystem.service","c":"AuthorService","l":"AuthorService(AuthorRepository)","u":"%3Cinit%3E(com.jacobrymsza.librarymanagementsystem.repository.AuthorRepository)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Book","l":"Book()","u":"%3Cinit%3E()"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Book","l":"Book(String, String)","u":"%3Cinit%3E(java.lang.String,java.lang.String)"},{"p":"com.jacobrymsza.librarymanagementsystem.controller","c":"BookController","l":"BookController(BookService)","u":"%3Cinit%3E(com.jacobrymsza.librarymanagementsystem.service.BookService)"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"BookDTO","l":"BookDTO(Long, String, String, List<String>)","u":"%3Cinit%3E(java.lang.Long,java.lang.String,java.lang.String,java.util.List)"},{"p":"com.jacobrymsza.librarymanagementsystem.service","c":"BookService","l":"BookService(BookRepository, AuthorRepository)","u":"%3Cinit%3E(com.jacobrymsza.librarymanagementsystem.repository.BookRepository,com.jacobrymsza.librarymanagementsystem.repository.AuthorRepository)"},{"p":"com.jacobrymsza.librarymanagementsystem.controller","c":"BorrowingController","l":"borrowBook(BorrowRequestDTO)","u":"borrowBook(com.jacobrymsza.librarymanagementsystem.dto.BorrowRequestDTO)"},{"p":"com.jacobrymsza.librarymanagementsystem.service","c":"BorrowingService","l":"borrowBook(Long, Long)","u":"borrowBook(java.lang.Long,java.lang.Long)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Borrowing","l":"Borrowing()","u":"%3Cinit%3E()"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Borrowing","l":"Borrowing(Book, User, LocalDateTime)","u":"%3Cinit%3E(com.jacobrymsza.librarymanagementsystem.entity.Book,com.jacobrymsza.librarymanagementsystem.entity.User,java.time.LocalDateTime)"},{"p":"com.jacobrymsza.librarymanagementsystem.controller","c":"BorrowingController","l":"BorrowingController(BorrowingService)","u":"%3Cinit%3E(com.jacobrymsza.librarymanagementsystem.service.BorrowingService)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"BorrowingDetails","l":"BorrowingDetails()","u":"%3Cinit%3E()"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"BorrowingDetails","l":"BorrowingDetails(User, String)","u":"%3Cinit%3E(com.jacobrymsza.librarymanagementsystem.entity.User,java.lang.String)"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"BorrowingDTO","l":"BorrowingDTO(Long, String, String, LocalDateTime, LocalDateTime)","u":"%3Cinit%3E(java.lang.Long,java.lang.String,java.lang.String,java.time.LocalDateTime,java.time.LocalDateTime)"},{"p":"com.jacobrymsza.librarymanagementsystem.service","c":"BorrowingService","l":"BorrowingService(BorrowingRepository, BookRepository, UserRepository)","u":"%3Cinit%3E(com.jacobrymsza.librarymanagementsystem.repository.BorrowingRepository,com.jacobrymsza.librarymanagementsystem.repository.BookRepository,com.jacobrymsza.librarymanagementsystem.repository.UserRepository)"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"BorrowRequestDTO","l":"BorrowRequestDTO()","u":"%3Cinit%3E()"},{"p":"com.jacobrymsza.librarymanagementsystem.controller","c":"AuthorController","l":"createAuthor(AuthorDTO)","u":"createAuthor(com.jacobrymsza.librarymanagementsystem.dto.AuthorDTO)"},{"p":"com.jacobrymsza.librarymanagementsystem.service","c":"AuthorService","l":"createAuthor(AuthorDTO)","u":"createAuthor(com.jacobrymsza.librarymanagementsystem.dto.AuthorDTO)"},{"p":"com.jacobrymsza.librarymanagementsystem.controller","c":"BookController","l":"createBook(BookDTO)","u":"createBook(com.jacobrymsza.librarymanagementsystem.dto.BookDTO)"},{"p":"com.jacobrymsza.librarymanagementsystem.service","c":"BookService","l":"createBook(BookDTO)","u":"createBook(com.jacobrymsza.librarymanagementsystem.dto.BookDTO)"},{"p":"com.jacobrymsza.librarymanagementsystem.controller","c":"AuthorController","l":"deleteAuthor(Long)","u":"deleteAuthor(java.lang.Long)"},{"p":"com.jacobrymsza.librarymanagementsystem.service","c":"AuthorService","l":"deleteAuthor(Long)","u":"deleteAuthor(java.lang.Long)"},{"p":"com.jacobrymsza.librarymanagementsystem.controller","c":"BookController","l":"deleteBook(Long)","u":"deleteBook(java.lang.Long)"},{"p":"com.jacobrymsza.librarymanagementsystem.service","c":"BookService","l":"deleteBook(Long)","u":"deleteBook(java.lang.Long)"},{"p":"com.jacobrymsza.librarymanagementsystem.repository","c":"AuthorRepository","l":"findByFirstNameAndLastName(String, String)","u":"findByFirstNameAndLastName(java.lang.String,java.lang.String)"},{"p":"com.jacobrymsza.librarymanagementsystem.repository","c":"BookRepository","l":"findByIsbn(String)","u":"findByIsbn(java.lang.String)"},{"p":"com.jacobrymsza.librarymanagementsystem.repository","c":"AuthorRepository","l":"findByLastName(String)","u":"findByLastName(java.lang.String)"},{"p":"com.jacobrymsza.librarymanagementsystem.repository","c":"BorrowingRepository","l":"findByUserId(Long)","u":"findByUserId(java.lang.Long)"},{"p":"com.jacobrymsza.librarymanagementsystem.repository","c":"UserRepository","l":"findByUsername(String)","u":"findByUsername(java.lang.String)"},{"p":"com.jacobrymsza.librarymanagementsystem.controller","c":"AuthorController","l":"getAllAuthors()"},{"p":"com.jacobrymsza.librarymanagementsystem.service","c":"AuthorService","l":"getAllAuthors()"},{"p":"com.jacobrymsza.librarymanagementsystem.controller","c":"BookController","l":"getAllBooks()"},{"p":"com.jacobrymsza.librarymanagementsystem.controller","c":"HelloController","l":"getAllBooks()"},{"p":"com.jacobrymsza.librarymanagementsystem.service","c":"BookService","l":"getAllBooks()"},{"p":"com.jacobrymsza.librarymanagementsystem.controller","c":"AuthorController","l":"getAuthorById(Long)","u":"getAuthorById(java.lang.Long)"},{"p":"com.jacobrymsza.librarymanagementsystem.service","c":"AuthorService","l":"getAuthorById(Long)","u":"getAuthorById(java.lang.Long)"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"BookDTO","l":"getAuthorNames()"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Book","l":"getAuthors()"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Borrowing","l":"getBook()"},{"p":"com.jacobrymsza.librarymanagementsystem.service","c":"BookService","l":"getBookById(Long)","u":"getBookById(java.lang.Long)"},{"p":"com.jacobrymsza.librarymanagementsystem.service","c":"BookService","l":"getBookByIsbn(String)","u":"getBookByIsbn(java.lang.String)"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"BorrowRequestDTO","l":"getBookId()"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Author","l":"getBooks()"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"BorrowingDTO","l":"getBookTitle()"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"BorrowingDTO","l":"getBorrowDate()"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Borrowing","l":"getBorrowDate()"},{"p":"com.jacobrymsza.librarymanagementsystem.controller","c":"BorrowingController","l":"getBorrowingById(Long)","u":"getBorrowingById(java.lang.Long)"},{"p":"com.jacobrymsza.librarymanagementsystem.service","c":"BorrowingService","l":"getBorrowingById(Long)","u":"getBorrowingById(java.lang.Long)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"User","l":"getBorrowingDetails()"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Book","l":"getBorrowings()"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"User","l":"getBorrowings()"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"BorrowingDetails","l":"getContactPhone()"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"User","l":"getEmail()"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"AuthorDTO","l":"getFirstName()"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Author","l":"getFirstName()"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"AuthorDTO","l":"getId()"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"BookDTO","l":"getId()"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"BorrowingDTO","l":"getId()"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Author","l":"getId()"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Book","l":"getId()"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Borrowing","l":"getId()"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"BorrowingDetails","l":"getId()"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"User","l":"getId()"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"BookDTO","l":"getIsbn()"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Book","l":"getIsbn()"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"AuthorDTO","l":"getLastName()"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Author","l":"getLastName()"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"BorrowingDTO","l":"getReturnDate()"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Borrowing","l":"getReturnDate()"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"BookDTO","l":"getTitle()"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Book","l":"getTitle()"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Borrowing","l":"getUser()"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"BorrowingDetails","l":"getUser()"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"BorrowRequestDTO","l":"getUserId()"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"User","l":"getUsername()"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"BorrowingDTO","l":"getUserUsername()"},{"p":"com.jacobrymsza.librarymanagementsystem.controller","c":"HelloController","l":"HelloController(BookService)","u":"%3Cinit%3E(com.jacobrymsza.librarymanagementsystem.service.BookService)"},{"p":"com.jacobrymsza.librarymanagementsystem.controller","c":"HelloController","l":"helloPage()"},{"p":"com.jacobrymsza.librarymanagementsystem","c":"LibraryManagementSystemApplication","l":"LibraryManagementSystemApplication()","u":"%3Cinit%3E()"},{"p":"com.jacobrymsza.librarymanagementsystem","c":"LibraryManagementSystemApplication","l":"main(String[])","u":"main(java.lang.String[])"},{"p":"com.jacobrymsza.librarymanagementsystem.controller","c":"BorrowingController","l":"returnBook(Long)","u":"returnBook(java.lang.Long)"},{"p":"com.jacobrymsza.librarymanagementsystem.service","c":"BorrowingService","l":"returnBook(Long)","u":"returnBook(java.lang.Long)"},{"p":"com.jacobrymsza.librarymanagementsystem.service","c":"AuthorService","l":"saveAuthor(Author)","u":"saveAuthor(com.jacobrymsza.librarymanagementsystem.entity.Author)"},{"p":"com.jacobrymsza.librarymanagementsystem.service","c":"BookService","l":"saveBook(Book)","u":"saveBook(com.jacobrymsza.librarymanagementsystem.entity.Book)"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"BookDTO","l":"setAuthorNames(List<String>)","u":"setAuthorNames(java.util.List)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Book","l":"setAuthors(List<Author>)","u":"setAuthors(java.util.List)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Borrowing","l":"setBook(Book)","u":"setBook(com.jacobrymsza.librarymanagementsystem.entity.Book)"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"BorrowRequestDTO","l":"setBookId(Long)","u":"setBookId(java.lang.Long)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Author","l":"setBooks(List<Book>)","u":"setBooks(java.util.List)"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"BorrowingDTO","l":"setBookTitle(String)","u":"setBookTitle(java.lang.String)"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"BorrowingDTO","l":"setBorrowDate(LocalDateTime)","u":"setBorrowDate(java.time.LocalDateTime)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Borrowing","l":"setBorrowDate(LocalDateTime)","u":"setBorrowDate(java.time.LocalDateTime)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"User","l":"setBorrowingDetails(BorrowingDetails)","u":"setBorrowingDetails(com.jacobrymsza.librarymanagementsystem.entity.BorrowingDetails)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Book","l":"setBorrowings(List<Borrowing>)","u":"setBorrowings(java.util.List)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"User","l":"setBorrowings(List<Borrowing>)","u":"setBorrowings(java.util.List)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"BorrowingDetails","l":"setContactPhone(String)","u":"setContactPhone(java.lang.String)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"User","l":"setEmail(String)","u":"setEmail(java.lang.String)"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"AuthorDTO","l":"setFirstName(String)","u":"setFirstName(java.lang.String)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Author","l":"setFirstName(String)","u":"setFirstName(java.lang.String)"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"AuthorDTO","l":"setId(Long)","u":"setId(java.lang.Long)"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"BookDTO","l":"setId(Long)","u":"setId(java.lang.Long)"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"BorrowingDTO","l":"setId(Long)","u":"setId(java.lang.Long)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Author","l":"setId(Long)","u":"setId(java.lang.Long)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Book","l":"setId(Long)","u":"setId(java.lang.Long)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Borrowing","l":"setId(Long)","u":"setId(java.lang.Long)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"BorrowingDetails","l":"setId(Long)","u":"setId(java.lang.Long)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"User","l":"setId(Long)","u":"setId(java.lang.Long)"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"BookDTO","l":"setIsbn(String)","u":"setIsbn(java.lang.String)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Book","l":"setIsbn(String)","u":"setIsbn(java.lang.String)"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"AuthorDTO","l":"setLastName(String)","u":"setLastName(java.lang.String)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Author","l":"setLastName(String)","u":"setLastName(java.lang.String)"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"BorrowingDTO","l":"setReturnDate(LocalDateTime)","u":"setReturnDate(java.time.LocalDateTime)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Borrowing","l":"setReturnDate(LocalDateTime)","u":"setReturnDate(java.time.LocalDateTime)"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"BookDTO","l":"setTitle(String)","u":"setTitle(java.lang.String)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Book","l":"setTitle(String)","u":"setTitle(java.lang.String)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"Borrowing","l":"setUser(User)","u":"setUser(com.jacobrymsza.librarymanagementsystem.entity.User)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"BorrowingDetails","l":"setUser(User)","u":"setUser(com.jacobrymsza.librarymanagementsystem.entity.User)"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"BorrowRequestDTO","l":"setUserId(Long)","u":"setUserId(java.lang.Long)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"User","l":"setUsername(String)","u":"setUsername(java.lang.String)"},{"p":"com.jacobrymsza.librarymanagementsystem.dto","c":"BorrowingDTO","l":"setUserUsername(String)","u":"setUserUsername(java.lang.String)"},{"p":"com.jacobrymsza.librarymanagementsystem.controller","c":"AuthorController","l":"updateAuthor(Long, AuthorDTO)","u":"updateAuthor(java.lang.Long,com.jacobrymsza.librarymanagementsystem.dto.AuthorDTO)"},{"p":"com.jacobrymsza.librarymanagementsystem.service","c":"AuthorService","l":"updateAuthor(Long, AuthorDTO)","u":"updateAuthor(java.lang.Long,com.jacobrymsza.librarymanagementsystem.dto.AuthorDTO)"},{"p":"com.jacobrymsza.librarymanagementsystem.controller","c":"BookController","l":"updateBook(Long, BookDTO)","u":"updateBook(java.lang.Long,com.jacobrymsza.librarymanagementsystem.dto.BookDTO)"},{"p":"com.jacobrymsza.librarymanagementsystem.service","c":"BookService","l":"updateBook(Long, BookDTO)","u":"updateBook(java.lang.Long,com.jacobrymsza.librarymanagementsystem.dto.BookDTO)"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"User","l":"User()","u":"%3Cinit%3E()"},{"p":"com.jacobrymsza.librarymanagementsystem.entity","c":"User","l":"User(String, String)","u":"%3Cinit%3E(java.lang.String,java.lang.String)"}];updateSearchResults();